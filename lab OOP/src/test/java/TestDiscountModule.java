
import org.junit.Assert;
import org.junit.Test;
import sale.enums.ProductType;
import sale.model.Basket;
import sale.model.TotalDiscount;
import sale.service.impl.DiscountService;

public class TestDiscountModule {

    @Test
    public void Get_Discount_For_Two_Products_A_And_B() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B);
        Assert.assertTrue(basket.getDiscount() >= 75 && basket.getDiscount() <= 76);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 750 && basket.getDiscount() <= 751);
        Assert.assertTrue(basket.getTotalPrice() >= 675 && basket.getDiscount() <= 676);
    }

    @Test
    public void Get_Discount_For_Two_Products_A_And_A() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.A);
        Assert.assertTrue(basket.getDiscount() >= 0 && basket.getDiscount() <= 1);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 600 && basket.getDiscount() <= 601);
        Assert.assertTrue(basket.getTotalPrice() >= 600 && basket.getDiscount() <= 601);
    }

    @Test
    public void Get_Discount_For_Three_Products_A_B_And_C() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.C);
        Assert.assertTrue(basket.getDiscount() >= 75 && basket.getDiscount() <= 76);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 3094 && basket.getDiscount() <= 3095);
        Assert.assertTrue(basket.getTotalPrice() >= 3019 && basket.getDiscount() <= 3020);
    }

    @Test
    public void Get_Discount_For_Three_Products_A_B_And_K() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.K);
        Assert.assertTrue(basket.getDiscount() >= 1570 && basket.getDiscount() <= 1571);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 30653 && basket.getDiscount() <= 30654);
        Assert.assertTrue(basket.getTotalPrice() >= 29082 && basket.getDiscount() <= 29083);
    }

    @Test
    public void Get_Discount_For_Four_Products_A_B_K_And_L() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.K, ProductType.L);
        Assert.assertTrue(basket.getDiscount() >= 3144 && basket.getDiscount() <= 3145);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 31885 && basket.getDiscount() <= 31885);
        Assert.assertTrue(basket.getTotalPrice() >= 28740 && basket.getDiscount() <= 28741);
    }

    @Test
    public void Get_Discount_For_Six_Products_A_B_K_L_A_And_B() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.K,
                ProductType.L, ProductType.A, ProductType.B);
        Assert.assertTrue(basket.getDiscount() >= 4799 && basket.getDiscount() <= 4800);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 32635 && basket.getDiscount() <= 32636);
        Assert.assertTrue(basket.getTotalPrice() >= 27835 && basket.getDiscount() <= 27836);
    }

    @Test
    public void Get_Discount_For_Six_Products_A_B_K_L_A_B_And_Total_Discount_For_Pensioners() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.K,
                ProductType.L, ProductType.A, ProductType.B);
        basket.addTotalDiscount(new TotalDiscount("For pensioners", 500));
        Assert.assertTrue(basket.getDiscount() >= 5299 && basket.getDiscount() <= 5300);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 32635 && basket.getDiscount() <= 32636);
        Assert.assertTrue(basket.getTotalPrice() >= 27335 && basket.getDiscount() <= 27336);
    }

    @Test
    public void Get_Discount_For_Six_Products_A_B_K_L_A_B_And_Two_Total_Discounts() {
        DiscountService discountService = new DiscountService();
        Basket basket = discountService.calculateCast(ProductType.A, ProductType.B, ProductType.K,
                ProductType.L, ProductType.A, ProductType.B);
        basket.addTotalDiscount(new TotalDiscount("For pensioners", 500));
        basket.addTotalDiscount(new TotalDiscount("Birthday", 1000));
        Assert.assertTrue(basket.getDiscount() >= 6299 && basket.getDiscount() <= 6300);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 32635 && basket.getDiscount() <= 32636);
        Assert.assertTrue(basket.getTotalPrice() >= 26335 && basket.getDiscount() <= 26336);
    }
}
