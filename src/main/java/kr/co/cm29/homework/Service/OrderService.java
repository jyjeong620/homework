package kr.co.cm29.homework.Service;

import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    public List<ProductDto> findAllProduct();
    public BigDecimal findByPrice(int productNumber);
    public String findByProductName(int productNumber);
    public void findByAmount(int productNumber, int amount) throws SoldOutException;
}
