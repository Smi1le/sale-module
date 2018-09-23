import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.lamberto.junit.GuiceJUnitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import sale.enums.ProductType;
import sale.facade.ISaleFacade;
import sale.facade.impl.SaleFacade;
import sale.factory.IDiscountFactory;
import sale.factory.impl.DiscountFactory;
import sale.model.Backet;
import sale.service.IDiscountService;
import sale.service.impl.DiscountService;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules(TestDiscountModule.TestModule.class)
public class TestDiscountModule {

    @Inject
    private ISaleFacade saleFacade;

    public static class TestModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(IDiscountFactory.class).to(DiscountFactory.class).in(Singleton.class);
            bind(ISaleFacade.class).to(SaleFacade.class);
            bind(IDiscountService.class).to(DiscountService.class);
        }
    }

    @Test
    public void Get_Discount_For_Two_Products_A_And_B() {
        Backet backet = saleFacade.calculateCost(ProductType.A, ProductType.B);
        Assert.assertTrue(backet.getDiscount() >= 75 && backet.getDiscount() <= 76);
        Assert.assertTrue(backet.getPriceWithoutDiscount() >= 750 && backet.getDiscount() <= 751);
        Assert.assertTrue(backet.getTotalPrice() >= 675 && backet.getDiscount() <= 676);
    }

    @Test
    public void Get_Discount_For_Two_Products_A_And_A() {
        Backet backet = saleFacade.calculateCost(ProductType.A, ProductType.A);
        Assert.assertTrue(backet.getDiscount() >= 0 && backet.getDiscount() <= 1);
        Assert.assertTrue(backet.getPriceWithoutDiscount() >= 600 && backet.getDiscount() <= 601);
        Assert.assertTrue(backet.getTotalPrice() >= 600 && backet.getDiscount() <= 601);
    }

    @Test
    public void Get_Discount_For_Three_Products_A_B_And_C() {
        Backet backet = saleFacade.calculateCost(ProductType.A, ProductType.B, ProductType.C);
        Assert.assertTrue(backet.getDiscount() >= 75 && backet.getDiscount() <= 76);
        Assert.assertTrue(backet.getPriceWithoutDiscount() >= 3094 && backet.getDiscount() <= 3095);
        Assert.assertTrue(backet.getTotalPrice() >= 3019 && backet.getDiscount() <= 3020);
    }

    @Test
    public void Get_Discount_For_Three_Products_A_B_And_K() {
        Backet backet = saleFacade.calculateCost(ProductType.A, ProductType.B, ProductType.K);
        Assert.assertTrue(backet.getDiscount() >= 1570 && backet.getDiscount() <= 1571);
        Assert.assertTrue(backet.getPriceWithoutDiscount() >= 30653 && backet.getDiscount() <= 30654);
        Assert.assertTrue(backet.getTotalPrice() >= 29082 && backet.getDiscount() <= 29083);
    }

    @Test
    public void Get_Discount_For_Four_Products_A_B_K_And_L() {
        Backet backet = saleFacade.calculateCost(ProductType.A, ProductType.B, ProductType.K, ProductType.L);
        Assert.assertTrue(backet.getDiscount() >= 3144 && backet.getDiscount() <= 3145);
        Assert.assertTrue(backet.getPriceWithoutDiscount() >= 31885 && backet.getDiscount() <= 31885);
        Assert.assertTrue(backet.getTotalPrice() >= 28740 && backet.getDiscount() <= 28741);
    }

    @Test
    public void Get_Discount_For_Six_Products_A_B_K_L_A_And_B() {
        Backet backet = saleFacade.calculateCost(ProductType.A, ProductType.B, ProductType.K,
                ProductType.L, ProductType.A, ProductType.B);
        Assert.assertTrue(backet.getDiscount() >= 4799 && backet.getDiscount() <= 4800);
        Assert.assertTrue(backet.getPriceWithoutDiscount() >= 32635 && backet.getDiscount() <= 32636);
        Assert.assertTrue(backet.getTotalPrice() >= 27835 && backet.getDiscount() <= 27836);
    }
}
