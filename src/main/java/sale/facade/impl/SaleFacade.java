package sale.facade.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import sale.enums.ProductType;
import sale.facade.ISaleFacade;
import sale.model.Backet;
import sale.service.IDiscountService;

import java.util.Arrays;
import java.util.List;

@Singleton
public class SaleFacade implements ISaleFacade {

    private final IDiscountService saleService;

    @Inject
    public SaleFacade(IDiscountService saleService) {
        this.saleService = saleService;
    }

    public Backet calculateCost(ProductType... productTypes) {
        List<ProductType> productTypeList = Arrays.asList(productTypes);
        return saleService.createBacket(productTypeList);
    }

    public Backet test() {
        return null;
    }
}
