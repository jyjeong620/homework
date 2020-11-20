package kr.co.cm29.homework.repository;

import kr.co.cm29.homework.model.ProductDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class OrderRepository {
    private final List<ProductDto> productList;

    /**
     * 모든 상품정보를 return
     * @return List<ProductDto>
     */
    public List<ProductDto> findAll(){
        return productList;
    }

    /**
     * 상품 번호를 받아와 출력
     * @param productNumber 상품번호
     * @return 상품판매가격
     */
    public BigDecimal findById(int productNumber){
        for(ProductDto data : productList){
            if(data.getProductNumber() == productNumber){
                return data.getPrice();
            }
        }
        return BigDecimal.ZERO;
    }
}
