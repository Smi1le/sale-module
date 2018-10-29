package sale.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import sale.enums.ProductType;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Discount {

    /**
     * List product types
     */
    private List<ProductType> productTypes;

    /**
     * Discount priority
     */
    private long priority;

    /**
     * Discount amount
     */
    private long discountPercent;

    public Discount(long priority, long discountPercent, ProductType... types) {
        this.productTypes = Arrays.asList(types);
        this.priority = priority;
        this.discountPercent = discountPercent;
    }

}
