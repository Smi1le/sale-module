package sale;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import sale.facade.ISaleFacade;
import sale.facade.impl.SaleFacade;
import sale.factory.IDiscountFactory;
import sale.factory.impl.DiscountFactory;
import sale.service.IDiscountService;
import sale.service.impl.DiscountService;

public class DiscountModule extends AbstractModule {
    protected void configure() {
        //add configuration logic here
        bind(IDiscountFactory.class).to(DiscountFactory.class).in(Singleton.class);
        bind(ISaleFacade.class).to(SaleFacade.class);
        bind(IDiscountService.class).to(DiscountService.class);
    }
}
