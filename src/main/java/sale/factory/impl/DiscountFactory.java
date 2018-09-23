package sale.factory.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;
import sale.enums.ProductType;
import sale.factory.IDiscountFactory;
import sale.model.Discount;

import java.util.Collection;

public class DiscountFactory implements IDiscountFactory {

    private Multimap<ProductType, Discount> discountsMap;

    @Inject
    public DiscountFactory() {
        discountsMap = ArrayListMultimap.create();
        addDiscountsForAAndBProductType();
        addDiscountsForDAndEProductType();
        addDiscountsForEFAndGProductType();
        addDiscountsForKLOrMProductType();
    }

    @Override
    public Collection<Discount> get(ProductType type) {
        return discountsMap.get(type);
    }

    private void addDiscountsForAAndBProductType() {
        discountsMap.put(ProductType.A, new Discount(1, 10, ProductType.A, ProductType.B));
        discountsMap.put(ProductType.B, new Discount(1, 10, ProductType.A, ProductType.B));
    }

    private void addDiscountsForDAndEProductType() {
        discountsMap.put(ProductType.D, new Discount(2, 5, ProductType.D, ProductType.E));
        discountsMap.put(ProductType.E, new Discount(2, 5, ProductType.D, ProductType.E));
    }

    private void addDiscountsForEFAndGProductType() {
        discountsMap.put(ProductType.E, new Discount(3, 5, ProductType.E, ProductType.F, ProductType.G));
        discountsMap.put(ProductType.F, new Discount(3, 5, ProductType.E, ProductType.F, ProductType.G));
        discountsMap.put(ProductType.G, new Discount(3, 5, ProductType.E, ProductType.F, ProductType.G));
    }

    private void addDiscountsForKLOrMProductType() {
        discountsMap.put(ProductType.A, new Discount(4, 5, ProductType.K));
        discountsMap.put(ProductType.A, new Discount(4, 5, ProductType.L));
        discountsMap.put(ProductType.A, new Discount(4, 5, ProductType.M));
    }



}
