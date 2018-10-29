
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sale.enums.ProductType;
import sale.model.Basket;
import sale.service.IDiscountService;
import sale.service.impl.DiscountService;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        IDiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.K, ProductType.L, ProductType.A, ProductType.B);
        log.info("basket.products = {}", basket.getProducts());
        log.info("basket.priceWithoutDiscount = {}", basket.getPriceWithoutDiscount());
        log.info("basket.dicsount = {}", basket.getDiscount());
        log.info("basket.totalPrice = {}", basket.getTotalPrice());
    }
}
