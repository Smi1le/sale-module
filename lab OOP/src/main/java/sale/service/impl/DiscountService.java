package sale.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import sale.builder.ProductBuilder;
import sale.enums.ProductType;
import sale.factory.IDiscountFactory;
import sale.factory.impl.DiscountFactory;
import sale.model.Basket;
import sale.model.Product;
import sale.model.TotalDiscount;
import sale.service.IDiscountService;
import sale.utils.DiscountCalculateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DiscountService implements IDiscountService {

    private HashMap<UUID, Basket> backets;
    private final IDiscountFactory saleFactory;

    public DiscountService() {
        this.saleFactory = new DiscountFactory();
        backets = new HashMap<>();
        BasicConfigurator.configure();
    }

    @Override
    public Basket calculateCast(ProductType... types) {
        return createBacket(Arrays.asList(types));
    }

    @Override
    public Basket createBacket(List<ProductType> productTypes) {
        List<Product> products = getProductsList(productTypes);
        Basket basket = new Basket(products);
        backets.put(basket.getId(), basket);
        basket.setRefreshCalcCallback(this::refreshCast);
        return calculateBacketCost(basket);
    }

    @Override
    public Basket getBacketById(UUID id) {
        return backets.get(id);
    }

    private Basket refreshCast(Basket basket) {
        List<TotalDiscount> totalDiscounts = basket.getTotalDiscounts();
        int discount = 0;
        for (TotalDiscount totalDiscount: totalDiscounts) {
            log.info("Total discount with name \'{}\' and cost {}", totalDiscount.getName(), totalDiscount.getTotalSum());
            discount += totalDiscount.getTotalSum();
        }
        basket.setDiscount(DiscountCalculateUtils.calculateDiscount(basket.getProducts()));
        basket.setTotalPrice(DiscountCalculateUtils.calculateTotalPrice(basket));
        basket.setDiscount(basket.getDiscount() + discount);
        basket.setTotalPrice(basket.getTotalPrice() - discount);
        return basket;
    }

    private Basket calculateBacketCost(Basket basket) {
        basket.setDiscount(DiscountCalculateUtils.calculateDiscount(basket.getProducts()));
        basket.setPriceWithoutDiscount(DiscountCalculateUtils.calculatePriceWithoutDiscount(basket.getProducts()));
        basket.setTotalPrice(DiscountCalculateUtils.calculateTotalPrice(basket));
        return basket;
    }

    private List<Product> getProductsList(List<ProductType> productTypes) {
        return productTypes
                .stream()
                .map(productType -> new ProductBuilder(productType, productType.getPrice())
                    .addSale(saleFactory.get(productType))
                    .build())
                .collect(Collectors.toList());
    }
}
