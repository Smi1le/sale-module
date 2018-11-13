package sale.service;

import sale.model.Basket;
import sale.model.Product;

import java.util.List;

public interface IDiscountService {

    /**
     * Receives the ProductTypes list at the input,
     * calculates their cost, discount, and the cost of the product,
     * taking with discounts, returns the object of the class Basket,
     * in which all information is stored.
     * @param products - List products
     * @return Basket
     */
    Basket calculateCast(Product... products);

    /**
     * Receives the ProductTypes list at the input,
     * calculates their cost, discount, and the cost of the product,
     * taking with discounts, returns the object of the class Basket,
     * in which all information is stored.
     * @param products - List products
     * @return Basket
     */
    Basket createBacket(List<Product> products);

    /**
     * add discount by product count
     * @param productNumber - product number
     * @param discount - discount in percent
     */
    void addDiscountByProductCount(Long productNumber, Long discount);

    /**
     * Add discount in system
     * @param priority - Priority
     * @param percent - Percent discount
     * @param products - List involved product in discount
     * @param productsApplies - List applies product in discount
     */
    void addDiscount(int priority, int percent, List<Product> products, List<Product> productsApplies);


    /**
     * Create product
     * @param name - product name
     * @param price - product price
     * @return
     */
    Product createProduct(String name, int price);

    /**
     * Add product in not participate discount list
     * @param product
     */
    void addProductInNotParticipateDiscountList(Product product);
}
