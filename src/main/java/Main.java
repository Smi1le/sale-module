import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sale.DiscountModule;
import sale.enums.ProductType;
import sale.facade.ISaleFacade;
import sale.model.Backet;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Main.class);
        Injector injector = Guice.createInjector(new DiscountModule());
        ISaleFacade saleFacade = injector.getInstance(ISaleFacade.class);
        Backet backet = saleFacade.calculateCost(ProductType.A, ProductType.B, ProductType.K, ProductType.L, ProductType.A, ProductType.B);
        logger.debug("backet.products", backet.getProducts());
        logger.debug("backet.priceWithoutDiscount", backet.getPriceWithoutDiscount());
        logger.debug("backet.dicsount", backet.getDiscount());
        logger.debug("backet.totalPrice", backet.getTotalPrice());
    }
}
