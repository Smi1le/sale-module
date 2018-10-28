package sale.factory;

import sale.enums.ProductType;
import sale.model.Discount;

import java.util.Collection;

public interface IDiscountFactory {

    Collection<Discount> get(ProductType type);
}
