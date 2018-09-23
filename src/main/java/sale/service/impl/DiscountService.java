package sale.service.impl;

import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import sale.builder.ProductBuilder;
import sale.enums.ProductType;
import sale.factory.IDiscountFactory;
import sale.model.Backet;
import sale.model.Product;
import sale.service.IDiscountService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class DiscountService implements IDiscountService {

    private HashMap<UUID, Backet> backets;
    private final IDiscountFactory saleFactory;

    @Inject
    public DiscountService(IDiscountFactory saleFactory) {
        this.saleFactory = saleFactory;
        backets = new HashMap<>();
    }

    @Override
    public Backet createBacket(List<ProductType> productTypes) {
        List<Product> products = getProductsList(productTypes);
        Backet backet = new Backet(products);
        backets.put(backet.getId(), backet);
        return backet;
    }

    @Override
    public Backet getBacketById(UUID id) {
        return backets.get(id);
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
