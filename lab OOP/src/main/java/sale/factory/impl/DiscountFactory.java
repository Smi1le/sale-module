package sale.factory.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import sale.enums.ProductType;
import sale.factory.IDiscountFactory;
import sale.model.Discount;

import java.util.Collection;

/**
 * Factory to build of discounts for certain product types
 */
public class DiscountFactory implements IDiscountFactory {

    private Multimap<ProductType, Discount> discountsMap;

    public DiscountFactory() {
        discountsMap = ArrayListMultimap.create();
        addDiscountsForAAndBProductType();
        addDiscountsForDAndEProductType();
        addDiscountsForEFAndGProductType();
        addDiscountsForKLOrMProductType();
    }

    /**
     * Return list discounts for certain product type
     * @param type - ProductType
     * @return Collection<Discount>
     */
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
