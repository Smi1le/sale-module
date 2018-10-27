package sale.utils;

import sale.enums.ProductType;
import sale.model.Backet;
import sale.model.Product;
import sale.model.Discount;

import java.util.*;
import java.util.stream.Collectors;

public class DiscountCalculateUtils {

    private static Map<Long, Long> mapDiscountByProductCount;
    private static List<ProductType> listProductTypesNotParticipateInQuantityDiscount;

    static {
        mapDiscountByProductCount = new HashMap<>();
        mapDiscountByProductCount.put(new Long(3), new Long(5));
        mapDiscountByProductCount.put(new Long(4), new Long(10));
        mapDiscountByProductCount.put(new Long(5), new Long(20));

        listProductTypesNotParticipateInQuantityDiscount = new ArrayList<>();
        listProductTypesNotParticipateInQuantityDiscount.add(ProductType.A);
        listProductTypesNotParticipateInQuantityDiscount.add(ProductType.C);
    }

    public static long calculatePriceWithoutDiscount(List<Product> productList) {
        return productList.stream().collect(Collectors.summarizingLong(Product::getPrice)).getSum();
    }

    public static double calculateTotalPrice(Backet backet) {
        double priceWithoutDiscount = backet.getPriceWithoutDiscount();
        double discount = backet.getDiscount();
        List<Product> products = backet.getProducts();
        double additionalDiscount =  DiscountCalculateUtils
                .calculateDiscountByProductCount(priceWithoutDiscount - discount, products);
        discount += additionalDiscount;
        backet.setDiscount(discount);
        return priceWithoutDiscount - discount;
    }

    public static double calculateDiscount(List<Product> productList) {
        markIsNotInDiscount(productList);
        double totalDiscount = 0;

        for (Product product: productList) {
            if (product.getAllreadyInDiscount()) {
                continue;
            }

            List<Discount> sortedDiscountByPriority = sortByPriority(product);
            List<ProductType> types = getProductTypesList(productList);

            for(int i = 0; i != sortedDiscountByPriority.size();) {
                Discount discount = sortedDiscountByPriority.get(i);
                boolean isContains = containsAll(productList, discount.getProductTypes());
                if (isContains) {
                    types.removeAll(discount.getProductTypes());
                    totalDiscount += calculateDiscountForProducts(discount);
                    continue;
                }
                ++i;
            }
        }
        return totalDiscount;
    }

    public static double calculateDiscountByProductCount(double allPrice, List<Product> products) {
            long countProductsInDicsount = products
                    .stream()
                    .map(Product::getType)
                    .filter(productType -> !listProductTypesNotParticipateInQuantityDiscount.contains(productType))
                    .collect(Collectors.summarizingLong(ProductType::getPrice)).getCount();

            long discount = Optional
                    .ofNullable(mapDiscountByProductCount.get(countProductsInDicsount))
                    .orElse(new Long(0));

            return calculateDiscount(allPrice, discount);
    }

    private static boolean containsAll(List<Product> products, List<ProductType> productTypes) {
        List<ProductType> coptProductTypes = new ArrayList<>(productTypes);
        int count = 0;
        for (Product product: products) {
            if (coptProductTypes .isEmpty()) {
                return true;
            }
            if (product.getAllreadyInDiscount()) {
                continue;
            }
            if (coptProductTypes .contains(product.getType())) {
                product.setAllreadyInDiscount(true);
                coptProductTypes.remove(product.getType());
                ++count;
            }
        }
        return coptProductTypes.isEmpty();
    }

    private static void markIsNotInDiscount(List<Product> products) {
        for (Product product : products) {
            product.setAllreadyInDiscount(false);
        }
    }

    private static double calculateDiscountForProducts(Discount sale) {
        double price = new Double(sale.getProductTypes()
                .stream()
                .collect(Collectors.summarizingLong(ProductType::getPrice))
                .getSum());
        return calculateDiscount(price, sale.getDiscountPercent());
    }

    private static double calculateDiscount(double price, long discount) {
        return (price / 100) *  discount;
    }

    private static List<Discount> sortByPriority(Product product) {
        return product
                .getDiscounts()
                .stream()
                .sorted(Comparator.comparing(Discount::getPriority))
                .collect(Collectors.toList());
    }

    private static List<ProductType> getProductTypesList(List<Product> products) {
        return products
                .stream()
                .map(Product::getType)
                .collect(Collectors.toList());
    }
}
