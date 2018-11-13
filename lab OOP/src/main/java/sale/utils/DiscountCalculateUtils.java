package sale.utils;

import sale.model.Basket;
import sale.model.Product;
import sale.model.Discount;

import java.util.*;
import java.util.stream.Collectors;

public class DiscountCalculateUtils {

    public static long calculatePriceWithoutDiscount(List<Product> productList) {
        return productList.stream().collect(Collectors.summarizingLong(Product::getPrice)).getSum();
    }

    public static double calculateTotalPrice(Basket basket, List<UUID> productNotParticipateInDiscount,
                                             Map<Long, Long> mapDiscountByProductCount) {
        double priceWithoutDiscount = basket.getPriceWithoutDiscount();
        double discount = basket.getDiscount();
        List<Product> products = basket.getProducts();
        double additionalDiscount =  DiscountCalculateUtils
                .calculateDiscountByProductCount(priceWithoutDiscount - discount,
                        products, productNotParticipateInDiscount, mapDiscountByProductCount);
        discount += additionalDiscount;
        basket.setDiscount(discount);
        return priceWithoutDiscount - discount;
    }

    public static double calculateDiscount(List<Product> productList, List<Product> allAvailableProducts,
                                           List<Discount> discounts) {
        markIsNotInDiscount(productList);
        double totalDiscount = 0;

        for (Product product: productList) {
            if (product.getAllreadyInDiscount()) {
                continue;
            }

            List<Discount> sortedDiscountByPriority = sortByPriority(product, discounts);
            List<Product> copyProductList = new ArrayList<>(productList);
            for(int i = 0; i != sortedDiscountByPriority.size();) {
                Discount discount = sortedDiscountByPriority.get(i);
                List<UUID> copyDiscountProductIds = new ArrayList<>(discount.getProductsApplies());
                boolean isContains = containsAll(copyProductList , copyDiscountProductIds);
                if (isContains) {
                    totalDiscount += calculateDiscountForProducts(discount, allAvailableProducts);
                    continue;
                }
                ++i;
                copyProductList = new ArrayList<>(productList);
            }
        }
        return totalDiscount;
    }

    public static double calculateDiscountByProductCount(double allPrice, List<Product> products,
                                                         List<UUID> productsNotParticipateInDiscount,
                                                         Map<Long, Long> mapDiscountByProductCount) {
            long countProductsInDicsount = products
                    .stream()
                    .filter(product -> !productsNotParticipateInDiscount.contains(product.getId()))
                    .collect(Collectors.summarizingLong(Product::getPrice)).getCount();

            long discount = Optional
                    .ofNullable(mapDiscountByProductCount.get(countProductsInDicsount))
                    .orElse(new Long(0));

            return calculateDiscount(allPrice, discount);
    }

    private static boolean containsAll(List<Product> products, List<UUID> copyProducts) {
        if (products.isEmpty() || copyProducts.isEmpty()) {
            return false;
        }
        for (Product product: products) {
            if (copyProducts.isEmpty()) {
                return true;
            }
            if (product.getAllreadyInDiscount()) {
                continue;
            }
            if (copyProducts.contains(product.getId())) {
                product.setAllreadyInDiscount(true);
                copyProducts.remove(product.getId());
            }
        }
        return copyProducts.isEmpty();
    }

    private static void markIsNotInDiscount(List<Product> products) {
        for (Product product : products) {
            product.setAllreadyInDiscount(false);
        }
    }

    private static double calculateDiscountForProducts(Discount sale, List<Product> allProducts) {
        double price = new Double(allProducts
                .stream()
                .filter(product -> sale.getProductsApplies().contains(product.getId()))
                .collect(Collectors.summarizingLong(Product::getPrice))
                .getSum());
        return calculateDiscount(price, sale.getDiscountPercent());
    }

    private static double calculateDiscount(double price, long discount) {
        return (price / 100) *  discount;
    }

    private static List<Discount> sortByPriority(Product product, List<Discount> discounts) {
        return discounts
                .stream()
                .filter(discount -> discount.getProductsInvolved().contains(product.getId()))
                .sorted(Comparator.comparing(Discount::getPriority))
                .collect(Collectors.toList());
    }
}
