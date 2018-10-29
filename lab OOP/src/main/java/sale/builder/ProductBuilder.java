package sale.builder;

import sale.enums.ProductType;
import sale.model.Product;
import sale.model.Discount;

import java.util.Collection;

/**
 * Builder for product
 */
public class ProductBuilder {

    private Product product;

    /**
     * Constructor for ProductBuilder Class
     * @param type - Product type
     * @param price - product price
     */
    public ProductBuilder(ProductType type, long price) {
        this.product = new Product(type, price);
    }

    /**
     * add active discount to the product
     * @param discount - active discount
     * @return ProductBuilder
     */
    public ProductBuilder addSale(Discount discount) {
        product.addSale(discount);
        return this;
    }

    /**
     * add list active discounts to the product
     * @param discounts - list active discounts
     * @return
     */
    public ProductBuilder addSale(Collection<Discount> discounts) {
        discounts.forEach(discount -> addSale(discount));
        return this;
    }

    /**
     * Return the build product
     * @return Produce
     */
    public Product build() {
        return product;
    }

}
