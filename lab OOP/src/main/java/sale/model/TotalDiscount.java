package sale.model;

import lombok.Data;

@Data
public class TotalDiscount {

    /**
     * Name discount
     */
    private String name;

    /**
     * total discount sum
     */
    private int totalSum;

    public TotalDiscount(String name, int totalSum) {
        this.name = name;
        this.totalSum = totalSum;
    }
}
