
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sale.enums.ProductType;
import sale.model.Backet;
import sale.service.IDiscountService;
import sale.service.impl.DiscountService;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger logger = LoggerFactory.getLogger(Main.class);
        IDiscountService discountService = new DiscountService();
        Backet backet = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.K, ProductType.L, ProductType.A, ProductType.B);
        log.info("backet.products = {}", backet.getProducts());
        log.info("backet.priceWithoutDiscount = {}", backet.getPriceWithoutDiscount());
        log.info("backet.dicsount = {}", backet.getDiscount());
        log.info("backet.totalPrice = {}", backet.getTotalPrice());
    }
}
