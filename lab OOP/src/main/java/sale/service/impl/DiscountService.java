package sale.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import sale.builder.ProductBuilder;
import sale.enums.ProductType;
import sale.factory.IDiscountFactory;
import sale.factory.impl.DiscountFactory;
import sale.model.Backet;
import sale.model.Product;
import sale.model.TotalDiscount;
import sale.service.IDiscountService;
import sale.utils.DiscountCalculateUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DiscountService implements IDiscountService {

    private HashMap<UUID, Backet> backets;
    private final IDiscountFactory saleFactory;

    public DiscountService() {
        this.saleFactory = new DiscountFactory();
        backets = new HashMap<>();
        BasicConfigurator.configure();
    }

    @Override
    public Backet calculateCast(ProductType... types) {
        return createBacket(Arrays.asList(types));
    }

    @Override
    public Backet createBacket(List<ProductType> productTypes) {
        List<Product> products = getProductsList(productTypes);
        Backet backet = new Backet(products);
        backets.put(backet.getId(), backet);
        backet.setRefreshCalcCallback(this::refreshCast);
        return calculateBacketCost(backet);
    }

    @Override
    public Backet getBacketById(UUID id) {
        return backets.get(id);
    }

    private Backet refreshCast(Backet backet) {
        List<TotalDiscount> totalDiscounts = backet.getTotalDiscounts();
        int discount = 0;
        for (TotalDiscount totalDiscount: totalDiscounts) {
            log.info("Total discount with name \'{}\' and cost {}", totalDiscount.getName(), totalDiscount.getTotalSum());
            discount += totalDiscount.getTotalSum();
        }
        backet.setDiscount(DiscountCalculateUtils.calculateDiscount(backet.getProducts()));
        backet.setTotalPrice(DiscountCalculateUtils.calculateTotalPrice(backet));
        backet.setDiscount(backet.getDiscount() + discount);
        backet.setTotalPrice(backet.getTotalPrice() - discount);
        return backet;
    }

    private Backet calculateBacketCost(Backet backet) {
        backet.setDiscount(DiscountCalculateUtils.calculateDiscount(backet.getProducts()));
        backet.setPriceWithoutDiscount(DiscountCalculateUtils.calculatePriceWithoutDiscount(backet.getProducts()));
        backet.setTotalPrice(DiscountCalculateUtils.calculateTotalPrice(backet));
        return backet;
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
