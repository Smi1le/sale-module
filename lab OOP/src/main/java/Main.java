
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;
import sale.model.Basket;
import sale.model.Product;
import sale.service.IDiscountService;
import sale.service.impl.DiscountService;

@Slf4j
public class Main {

    //ProductType.A, ProductType.B, ProductType.K, ProductType.L, ProductType.A, ProductType.B
    public static void main(String[] args) {
        BasicConfigurator.configure();
        IDiscountService discountService = new DiscountService();
        Product productA = discountService.createProduct("A", 300);
        Product productB = discountService.createProduct("B", 450);
        Product productC = discountService.createProduct("C", 2344);
        Product productK = discountService.createProduct("K", 29903);
        Product productL = discountService.createProduct("L", 1232);
        discountService.addDiscountByProductCount(new Long(3), new Long(5));
        discountService.addDiscountByProductCount(new Long(3), new Long(10));
        discountService.addDiscountByProductCount(new Long(5), new Long(20));
        discountService.addProductInNotParticipateDiscountList(productA);
        discountService.addProductInNotParticipateDiscountList(productC);

        Basket basket = discountService.calculateCast(productA, productB, productK, productL, productA, productB);
        log.info("basket.products = {}", basket.getProducts());
        log.info("basket.priceWithoutDiscount = {}", basket.getPriceWithoutDiscount());
        log.info("basket.dicsount = {}", basket.getDiscount());
        log.info("basket.totalPrice = {}", basket.getTotalPrice());
    }
}
