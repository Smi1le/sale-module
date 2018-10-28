package sale.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import sale.enums.ProductType;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Discount {

    private List<ProductType> productTypes;

    private long priority;

    private long discountPercent;

    public Discount(long priority, long discountPercent, ProductType... types) {
        this.productTypes = Arrays.asList(types);
        this.priority = priority;
        this.discountPercent = discountPercent;
    }

}
