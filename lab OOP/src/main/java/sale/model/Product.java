package sale.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import sale.enums.ProductType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Product {

    /**
     * ID
     */
    private UUID id;

    /**
     * Product type
     */
    private ProductType type;

    private long price;

    /**
     * All available discount for ProductType
     */
    private List<Discount> discounts;

    /**
     * Flag is product allready calculate in discount
     */
    private Boolean allreadyInDiscount = false;

    public Product(ProductType type, long price) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.price = price;
        discounts = new ArrayList<>();
    }

    public Product(Product product) {
        this.id = product.id;
        this.type = product.type;
        this.price = product.price;
        this.discounts = product.discounts;
        this.allreadyInDiscount = product.allreadyInDiscount;
    }

    public void addSale(Discount sale) {
        discounts.add(sale);
    }
}
