package sale.facade;

import sale.enums.ProductType;
import sale.model.Backet;

public interface ISaleFacade {

    Backet calculateCost(ProductType... productTypes);

    Backet test();
}
