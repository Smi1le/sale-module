package sale.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Data
@NoArgsConstructor
public class Backet {

    private UUID id;

    private List<Product> products;

    private List<TotalDiscount> totalDiscounts;

    private double priceWithoutDiscount;

    private double discount;

    private double totalPrice;

    private Function<Backet, Backet> refreshCalcCallback;

    public Backet(List<Product> products) {
        this.id = UUID.randomUUID();
        this.products = products;
        this.totalDiscounts = new ArrayList<>();
    }

    public void addTotalDiscount(TotalDiscount totalDiscount) {
        this.totalDiscounts.add(totalDiscount);
        Backet newVersion = refreshCalcCallback.apply(this);
        this.totalPrice = newVersion.totalPrice;
        this.priceWithoutDiscount = newVersion.priceWithoutDiscount;
        this.discount = newVersion.discount;
    }

}
