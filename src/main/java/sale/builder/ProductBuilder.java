package sale.builder;

import sale.enums.ProductType;
import sale.model.Product;
import sale.model.Discount;

import java.util.Collection;

public class ProductBuilder {

    private Product product;

    public ProductBuilder(ProductType type, long price) {
        this.product = new Product(type, price);
    }

    public ProductBuilder addSale(Discount discount) {
        product.addSale(discount);
        return this;
    }

    public ProductBuilder addSale(Collection<Discount> discounts) {
        discounts.forEach(discount -> product.addSale(discount));
        return this;
    }

    public Product build() {
        return product;
    }

}
