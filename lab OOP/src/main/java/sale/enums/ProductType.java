package sale.enums;

import lombok.Getter;

/**
 * Enum Product Types
 */
public enum ProductType {
    A("A", 300),
    B("B", 450),
    C("C", 2344),
    D("D", 999),
    E("E", 2385),
    F("F", 23902),
    J("J", 2323),
    H("H", 10),
    I("I", 912),
    G("G", 100000),
    K("K", 29903),
    L("L", 1232),
    M("M", 29890),
    N("N", 20943),
    O("O", 120),
    P("P", 90283);

    @Getter
    private String type;

    @Getter
    private long price;

    ProductType(String type, long price) {
        this.type = type;
        this.price = price;
    }

}
