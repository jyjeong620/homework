package kr.co.cm29.homework.Service;

import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<ProductDto> findAllProduct();
    BigDecimal findByPrice(int productNumber);
    String findByProductName(int productNumber);
    void findByAmount(int productNumber, int amount) throws SoldOutException;
}
