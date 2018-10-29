package sale.service;

import sale.enums.ProductType;
import sale.model.Basket;

import java.util.List;
import java.util.UUID;

public interface IDiscountService {

    /**
     * Receives the ProductTypes list at the input,
     * calculates their cost, discount, and the cost of the product,
     * taking with discounts, returns the object of the class Basket,
     * in which all information is stored.
     * @param types - List product types
     * @return Basket
     */
    Basket calculateCast(ProductType... types);

    /**
     * Receives the ProductTypes list at the input,
     * calculates their cost, discount, and the cost of the product,
     * taking with discounts, returns the object of the class Basket,
     * in which all information is stored.
     * @param productTypes - List product types
     * @return Basket
     */
    Basket createBacket(List<ProductType> productTypes);

    /**
     * Return Basket By Id
     * @param id - basket id
     * @return
     */
    Basket getBacketById(UUID id);
}
