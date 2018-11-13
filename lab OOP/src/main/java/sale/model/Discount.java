package sale.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Discount {

    /**
     * List involved products in discount
     */
    private List<UUID> productsInvolved;

    /**
     * List applies products in discounts
     */
    private List<UUID> productsApplies;

    /**
     * Discount priority
     */
    private long priority;

    /**
     * Discount amount
     */
    private long discountPercent;

    public Discount(long priority, long discountPercent, List<UUID> products, List<UUID> productsApplies) {
        this.productsInvolved = products;
        this.productsApplies = productsApplies;
        this.priority = priority;
        this.discountPercent = discountPercent;
    }

}
