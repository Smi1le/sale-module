package sale.service;

import sale.enums.ProductType;
import sale.model.Backet;

import java.util.List;
import java.util.UUID;

public interface IDiscountService {

    Backet calculateCast(ProductType... types);

    Backet createBacket(List<ProductType>productTypes);

    Backet getBacketById(UUID id);
}
