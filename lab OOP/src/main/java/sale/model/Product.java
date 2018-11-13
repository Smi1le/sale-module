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
    private String name;

    private long price;

    private boolean isParticipatesInDiscounts = true;

    /**
     * Flag is product allready calculate in discount
     */
    private Boolean allreadyInDiscount = false;

    public Product(String name, long price) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
    }

    public Product(Product product) {
        this.id = product.id;
        this.name = product.name;
        this.price = product.price;
        this.allreadyInDiscount = product.allreadyInDiscount;
    }
}
