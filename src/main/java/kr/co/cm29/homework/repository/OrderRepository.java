package kr.co.cm29.homework.repository;

import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class OrderRepository {
    private static OrderRepository instance;
    private final List<ProductDto> productList;

    private OrderRepository(List<ProductDto> productList) {
        this.productList = productList;
    }

    public synchronized static OrderRepository getInstance(List<ProductDto> productList){
        if(OrderRepository.instance == null){
            OrderRepository.instance = new OrderRepository(productList);
        }
        return OrderRepository.instance;
    }

    /**
     * 모든 상품정보를 return
     * @return List<ProductDto>
     */
    public List<ProductDto> findAll(){
        return productList;
    }

    /**
     * 상품 번호를 받아와 판매가격 return
     * @param productNumber 상품번호
     * @return 상품판매가격
     */
    public BigDecimal findByPrice(int productNumber){
        for(ProductDto data : productList){
            if(data.getProductNumber() == productNumber){
                return data.getPrice();
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * 상품번호를 받아와 상품명 return
     * @param productNumber 상품번호
     * @return 상품명
     */
    public String findByProductName(int productNumber){
        for(ProductDto data : productList){
            if(data.getProductNumber() == productNumber){
                return data.getProductName();
            }
        }
        return "";
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
                    log.error("재고수량 부족!!!!");
                    throw new SoldOutException();
                } else {
                    log.debug("남은 재고수량 : " + finalAmount);
                    data.setAmount(finalAmount);
                }
            }
        }
    }
}
