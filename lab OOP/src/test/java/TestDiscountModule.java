
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sale.model.Basket;
import sale.model.Product;
import sale.model.TotalDiscount;
import sale.service.impl.DiscountService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestDiscountModule {

    private DiscountService discountService;
    private Map<String, Product> productMap;

    @Before
    public void init() {
        discountService = new DiscountService();
        productMap = new HashMap<>();
        productMap.put("A", discountService.createProduct("A", 300));
        productMap.put("B", discountService.createProduct("B", 450));
        productMap.put("C", discountService.createProduct("C", 2344));
        productMap.put("D", discountService.createProduct("D", 999));
        productMap.put("E", discountService.createProduct("E", 2385));
        productMap.put("F", discountService.createProduct("F", 23902));
        productMap.put("J", discountService.createProduct("J", 2323));
        productMap.put("H", discountService.createProduct("H", 10));
        productMap.put("I", discountService.createProduct("I", 912));
        productMap.put("G", discountService.createProduct("G", 100000));
        productMap.put("K", discountService.createProduct("K", 29903));
        productMap.put("L", discountService.createProduct("L", 1232));
        productMap.put("M", discountService.createProduct("M", 29890));
        productMap.put("N", discountService.createProduct("N", 20943));
        productMap.put("O", discountService.createProduct("O", 120));
        productMap.put("P", discountService.createProduct("P", 90283));

        discountService.addDiscountByProductCount(new Long(3), new Long(5));
        discountService.addDiscountByProductCount(new Long(4), new Long(10));
        discountService.addDiscountByProductCount(new Long(5), new Long(20));

        discountService.addProductInNotParticipateDiscountList(productMap.get("A"));
        discountService.addProductInNotParticipateDiscountList(productMap.get("C"));

        discountService.addDiscount(1, 10, Arrays.asList(productMap.get("A"), productMap.get("B")),
                Arrays.asList(productMap.get("A"), productMap.get("B")));
        discountService.addDiscount(2, 5, Arrays.asList(productMap.get("D"), productMap.get("E")),
                Arrays.asList(productMap.get("D"), productMap.get("E")));
        discountService.addDiscount(3, 5, Arrays.asList(productMap.get("E"), productMap.get("F"), productMap.get("G")),
                Arrays.asList(productMap.get("E"), productMap.get("F"), productMap.get("G")));
        discountService.addDiscount(4, 5, Arrays.asList(productMap.get("K"), productMap.get("A")),
                Arrays.asList(productMap.get("K")));
        discountService.addDiscount(4, 5, Arrays.asList(productMap.get("L"), productMap.get("A")),
                Arrays.asList(productMap.get("L")));
        discountService.addDiscount(4, 5, Arrays.asList(productMap.get("M"), productMap.get("A")),
                Arrays.asList(productMap.get("M")));
    }

    @Test
    public void Get_Discount_For_Two_Products_A_And_B() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("B")));
        Assert.assertTrue(basket.getDiscount() >= 75 && basket.getDiscount() <= 76);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 750 && basket.getDiscount() <= 751);
        Assert.assertTrue(basket.getTotalPrice() >= 675 && basket.getDiscount() <= 676);
    }

    @Test
    public void Get_Discount_For_Two_Products_A_And_A() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("A")));
        Assert.assertTrue(basket.getDiscount() >= 0 && basket.getDiscount() <= 1);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 600 && basket.getDiscount() <= 601);
        Assert.assertTrue(basket.getTotalPrice() >= 600 && basket.getDiscount() <= 601);
    }

    @Test
    public void Get_Discount_For_Three_Products_A_B_And_C() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("B")), new Product(productMap.get("C")));
        Assert.assertTrue(basket.getDiscount() >= 75 && basket.getDiscount() <= 76);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 3094 && basket.getDiscount() <= 3095);
        Assert.assertTrue(basket.getTotalPrice() >= 3019 && basket.getDiscount() <= 3020);
    }

    @Test
    public void Get_Discount_For_Three_Products_A_B_And_K() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("B")), new Product(productMap.get("K")));
        Assert.assertTrue(basket.getDiscount() >= 1570 && basket.getDiscount() <= 1571);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 30653 && basket.getDiscount() <= 30654);
        Assert.assertTrue(basket.getTotalPrice() >= 29082 && basket.getDiscount() <= 29083);
    }

    @Test
    public void Get_Discount_For_Four_Products_A_B_K_And_L() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("B")), new Product(productMap.get("K")),
                new Product(productMap.get("L")));
        Assert.assertTrue(basket.getDiscount() >= 3144 && basket.getDiscount() <= 3145);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 31885 && basket.getDiscount() <= 31885);
        Assert.assertTrue(basket.getTotalPrice() >= 28740 && basket.getDiscount() <= 28741);
    }

    @Test
    public void Get_Discount_For_Six_Products_A_B_K_L_A_And_B() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("B")), new Product(productMap.get("K")),
                new Product(productMap.get("L")), new Product(productMap.get("A")), new Product(productMap.get("B")));
        Assert.assertTrue(basket.getDiscount() >= 4799 && basket.getDiscount() <= 4800);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 32635 && basket.getDiscount() <= 32636);
        Assert.assertTrue(basket.getTotalPrice() >= 27835 && basket.getDiscount() <= 27836);
    }

    @Test
    public void Get_Discount_For_Six_Products_A_B_K_L_A_B_And_Total_Discount_For_Pensioners() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("B")), new Product(productMap.get("K")),
                new Product(productMap.get("L")), new Product(productMap.get("A")), new Product(productMap.get("B")));
        basket.addTotalDiscount(new TotalDiscount("For pensioners", 500));
        Assert.assertTrue(basket.getDiscount() >= 5299 && basket.getDiscount() <= 5300);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 32635 && basket.getDiscount() <= 32636);
        Assert.assertTrue(basket.getTotalPrice() >= 27335 && basket.getDiscount() <= 27336);
    }

    @Test
    public void Get_Discount_For_Six_Products_A_B_K_L_A_B_And_Two_Total_Discounts() {
        Basket basket = discountService.calculateCast(new Product(productMap.get("A")), new Product(productMap.get("B")), new Product(productMap.get("K")),
                new Product(productMap.get("L")), new Product(productMap.get("A")), new Product(productMap.get("B")));
        basket.addTotalDiscount(new TotalDiscount("For pensioners", 500));
        basket.addTotalDiscount(new TotalDiscount("Birthday", 1000));
        Assert.assertTrue(basket.getDiscount() >= 6299 && basket.getDiscount() <= 6300);
        Assert.assertTrue(basket.getPriceWithoutDiscount() >= 32635 && basket.getDiscount() <= 32636);
        Assert.assertTrue(basket.getTotalPrice() >= 26335 && basket.getDiscount() <= 26336);
    }
}