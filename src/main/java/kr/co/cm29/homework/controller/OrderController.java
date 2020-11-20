package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.Service.OrderService;
import kr.co.cm29.homework.model.ProductDto;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    //TODO 모든 상품을 출력하는 메소드
    /**
     * 모든 상품을 출력
     */
    public void printAll(){
        System.out.printf("%s\t\t%-50s%s\t\t%s\n","상품번호","상품명","판매가격","재고수");
        for(ProductDto data : orderService.findAllProduct()){
            System.out.printf("%s\t\t%-50s%s\t\t%s\n",data.getProductNumber(),data.getProductName(),data.getPrice(),data.getAmount());
        }
    }
    //TODO 상품번호, 수량을 입력받아 해당 상품의 가격을 가져와 금액을 계산
    public BigDecimal findProductNumber(){

        return BigDecimal.ZERO;
    }

    //TODO 판매가격과 수량을 매계변수로 받아 최종금액을 계산
}
