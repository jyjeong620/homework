package kr.co.cm29.homework.repository;

import kr.co.cm29.homework.exception.SoldOutException;
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

    /**
     * 해당 상품의 재고 수량 빼기
     */
    public void subAmount(int productNumber, int amount) throws SoldOutException{
        int finalAmount = 0;
        for(ProductDto data : productList){
            if(data.getProductNumber() == productNumber){
                finalAmount = data.getAmount()- amount;
                if(finalAmount < 0){
                    System.out.println("재고수량 부족!!!!");
                    throw new SoldOutException();
                } else {
                    System.out.println("남은 재고수량 : " + finalAmount);
                    data.setAmount(finalAmount);
                }
            }
        }
    }
}
