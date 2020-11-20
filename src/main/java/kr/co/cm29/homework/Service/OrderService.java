package kr.co.cm29.homework.Service;

import kr.co.cm29.homework.model.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    public List<ProductDto> findAllProduct();
    public BigDecimal findById(int productNumber);
}
