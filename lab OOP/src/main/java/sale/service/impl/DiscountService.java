package sale.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import sale.model.Basket;
import sale.model.Discount;
import sale.model.Product;
import sale.model.TotalDiscount;
import sale.service.IDiscountService;
import sale.utils.DiscountCalculateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DiscountService implements IDiscountService {

    private HashMap<UUID, Basket> backets;

    /**
     * Additional discounts depending on the quantity of products to the basket
     */
    private Map<Long, Long> mapDiscountByProductCount;


    private List<Product> allAvailableProducts;

    private List<Discount> discounts;

    /**
     * List product types not participate in quantity discounts
     */
    private List<UUID> notParticipateInDiscount;

    public DiscountService() {
        backets = new HashMap<>();
        BasicConfigurator.configure();
        discounts = new ArrayList<>();
        allAvailableProducts = new ArrayList<>();
        notParticipateInDiscount = new ArrayList<>();
        mapDiscountByProductCount = new HashMap<>();
    }

    @Override
    public Basket calculateCast(Product... products) {
        return createBacket(Arrays.asList(products));
    }

    @Override
    public Basket createBacket(List<Product> products) {
        Basket basket = new Basket(products);
        backets.put(basket.getId(), basket);
        basket.setRefreshCalcCallback(this::refreshCast);
        return calculateBacketCost(basket);
    }

    @Override
    public void addDiscountByProductCount(Long productNumber, Long discount) {
        mapDiscountByProductCount.put(productNumber, discount);
    }

    @Override
    public void addDiscount(int priority, int percent, List<Product> productsInvolvde, List<Product> productsApplies) {
        List<UUID> productsIds = productsInvolvde.stream().map(Product::getId).collect(Collectors.toList());
        List<UUID> productsAppliesIds = productsApplies.stream().map(Product::getId).collect(Collectors.toList());
        discounts.add(new Discount(priority, percent, productsIds, productsAppliesIds));
    }

    @Override
    public Product createProduct(String name, int price) {
        Product product = new Product(name, price);
        allAvailableProducts.add(product);
        return product;
    }

    @Override
    public void addProductInNotParticipateDiscountList(Product product) {
        notParticipateInDiscount.add(product.getId());
    }

    private Basket refreshCast(Basket basket) {
        List<TotalDiscount> totalDiscounts = basket.getTotalDiscounts();
        int discount = 0;
        for (TotalDiscount totalDiscount: totalDiscounts) {
            log.info("Total discount with name \'{}\' and cost {}", totalDiscount.getName(), totalDiscount.getTotalSum());
            discount += totalDiscount.getTotalSum();
        }
        basket.setDiscount(DiscountCalculateUtils.calculateDiscount(basket.getProducts(), allAvailableProducts, discounts));
        basket.setTotalPrice(DiscountCalculateUtils.calculateTotalPrice(basket, notParticipateInDiscount,
                mapDiscountByProductCount));
        basket.setDiscount(basket.getDiscount() + discount);
        basket.setTotalPrice(basket.getTotalPrice() - discount);
        return basket;
    }

    private Basket calculateBacketCost(Basket basket) {
        basket.setDiscount(DiscountCalculateUtils.calculateDiscount(basket.getProducts(), allAvailableProducts, discounts));
        basket.setPriceWithoutDiscount(DiscountCalculateUtils.calculatePriceWithoutDiscount(basket.getProducts()));
        basket.setTotalPrice(DiscountCalculateUtils.calculateTotalPrice(basket, notParticipateInDiscount,
                mapDiscountByProductCount));
        return basket;
    }
}
