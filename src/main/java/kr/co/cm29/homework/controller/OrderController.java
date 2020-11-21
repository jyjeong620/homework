package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.Service.OrderService;
import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Scanner;

@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    Scanner scan;

    /**
     * 모든 상품을 출력
     */
    public void printAll(){
        System.out.printf("%s\t\t%-50s%s\t\t%s\n","상품번호","상품명","판매가격","재고수");
        for(ProductDto data : orderService.findAllProduct()){
            System.out.printf("%s\t\t%-50s%s\t\t%s\n",data.getProductNumber(),data.getProductName(),data.getPrice(),data.getAmount());
        }
    }

    /**
     * 상품번호, 수량을 입력받아 해당 상품의 가격을 가져와 금액을 계산
     */
    public BigDecimal findProductNumber() throws SoldOutException{
        BigDecimal price = BigDecimal.ZERO;
        scan = new Scanner(System.in);
        String productNumber;
        String amount;
        boolean shopping = true;

        while(shopping){
            System.out.print("상품번호 : ");
            productNumber = scan.nextLine();
            System.out.print("수량 : ");
            amount = scan.nextLine();
            if(productNumber.equals(" ") && amount.equals(" ")){
                log.debug("입력값 없음");
                shopping = false;
            } else if(productNumber.isEmpty() || amount.isEmpty()){
                System.out.println("잘못입력하셨습니다.");
            } else {
                price = calculatePrice(Integer.parseInt(productNumber), Integer.parseInt(amount));
            }
        }

        return price;
    }

    /**
     * 상품번호 수량을 매계변수로 받아 최종금액을 계산
     * 상품의 수량이 없을경우 soldOUtException 발생
     * @return 최종금액
     */
    public BigDecimal calculatePrice(int productNumber, int amount) throws SoldOutException {

        orderService.findByAmount(productNumber,amount);
        BigDecimal price = orderService.findById(productNumber);
        log.debug("상품번호 : " + productNumber);
        log.debug("상품금액 : " + price);
        log.debug("수량 : " + amount);
        return price.multiply(new BigDecimal(amount));
    }

    /**
     * 최종금액 출력 메소드
     * 최종금액이 5만원 미만일경우 배송료 추가
     */
    public void calculateFinalPrice(BigDecimal price){
        DecimalFormat formatter = new DecimalFormat("###,###");

        System.out.println("주문 금액 : " + formatter.format(price) + "원 ");
        if(new BigDecimal(50000).compareTo(price) > 0) {
            System.out.println("배송비 : 2,500원");
            price = price.add(new BigDecimal(2500));
        }
        System.out.println("지불 금액 : " + formatter.format(price) + "원");

    }

}
