package sale.model;

import lombok.Data;

@Data
public class TotalDiscount {

    private String name;

    private int totalSum;

    public TotalDiscount(String name, int totalSum) {
        this.name = name;
        this.totalSum = totalSum;
    }
}
