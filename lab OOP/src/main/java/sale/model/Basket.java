package sale.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Data
@NoArgsConstructor
public class Basket {

    /**
     * ID
     */
    private UUID id;

    /**
     * List product in backet
     */
    private List<Product> products;

    /**
     * List additional discount
     */
    private List<TotalDiscount> totalDiscounts;

    /**
     * Price without discounts
     */
    private double priceWithoutDiscount;

    /**
     * Discount
     */
    private double discount;

    /**
     * Total price for list product, including all discounts
     */
    private double totalPrice;


    private Function<Basket, Basket> refreshCalcCallback;

    public Basket(List<Product> products) {
        this.id = UUID.randomUUID();
        this.products = products;
        this.totalDiscounts = new ArrayList<>();
    }

    public void addTotalDiscount(TotalDiscount totalDiscount) {
        this.totalDiscounts.add(totalDiscount);
        Basket newVersion = refreshCalcCallback.apply(this);
        this.totalPrice = newVersion.totalPrice;
        this.priceWithoutDiscount = newVersion.priceWithoutDiscount;
        this.discount = newVersion.discount;
    }

}
