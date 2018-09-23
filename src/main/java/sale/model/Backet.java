package sale.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import sale.utils.DiscountCalculateUtils;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Backet {

    private UUID id;

    private List<Product> products;

    private double priceWithoutDiscount;

    private double discount;

    private double totalPrice;

    public Backet(List<Product> products) {
        this.id = UUID.randomUUID();
        this.products = products;
        this.priceWithoutDiscount = calculatePriceWithoutDiscount();
        this.discount = calculateDiscount();
        this.totalPrice = caltulateTotalPrice();

    }

    private long calculatePriceWithoutDiscount() {
        return DiscountCalculateUtils.calculatePriceWithoutDiscount(this.products);
    }

    private double calculateDiscount() {
        return DiscountCalculateUtils.calculateDiscount(this.products);
    }

    private double caltulateTotalPrice() {
        double additionalDiscount =  DiscountCalculateUtils
                .calculateDiscountByProductCount(priceWithoutDiscount - discount, products);
        discount += additionalDiscount;
        return priceWithoutDiscount - discount;
    }
}
