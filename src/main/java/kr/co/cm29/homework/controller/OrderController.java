package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.Service.OrderService;
import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class OrderController {
    private static OrderController instance;
    private final OrderService orderService;
    private Scanner scan;

    private OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public synchronized static OrderController getInstance(OrderService orderService){
        if(OrderController.instance == null){
            OrderController.instance = new OrderController(orderService);
        }
        return OrderController.instance;
    }


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
    public void findProductNumber() throws SoldOutException{
        List<ProductDto> orders = new ArrayList<>();
        this.scan = new Scanner(System.in);
        String productNumber;
        String amount;
        boolean isShopping = true;

        while(isShopping){
            System.out.print("상품번호 : ");
            productNumber = scan.nextLine();

            if(productNumber.equals(" ")){
                log.debug("입력값 없음");
                calculateOrders(orders);
                isShopping = false;
                continue;
            }
            System.out.print("수량 : ");
            amount = scan.nextLine();

            if(productNumber.isEmpty() || amount.isEmpty()
                    || !productNumber.matches("^[0-9]*$") || !amount.matches("^[0-9]*$")){
                System.out.println("잘못입력하셨습니다.");
            } else {
                orders.add(putUser(Integer.parseInt(productNumber), Integer.parseInt(amount)));
            }
        }

    }

    /**
     * 입력받은 상품번호와 수량을 저장
     */
    private ProductDto putUser(int productNumber, int amount){
        ProductDto user = new ProductDto();
        user.setProductNumber(productNumber);
        user.setAmount(amount);
        return user;
    }

    /**
     * 주문 상품을 입력받아 상품명, 금액 처리
     * 상품의 수량이 없을경우 soldOUtException 발생
     */
    public synchronized void calculateOrders(List<ProductDto> orders) throws SoldOutException {
        List<ProductDto> finalOrders = new ArrayList<>();
        for(ProductDto order : orders) {
            orderService.findByAmount(order.getProductNumber(), order.getAmount());
            order.setProductName(orderService.findByProductName(order.getProductNumber()));
            order.setPrice(orderService.findByPrice(order.getProductNumber()));
            log.debug("상품번호 : " + order.getProductNumber());
            log.debug("상품명 : " + order.getProductName());
            log.debug("수량 : " + order.getAmount());
            log.debug("금액 : " + order.getPrice());

            finalOrders.add(order);
        }
        calculateFinalPrice(finalOrders);
    }

    /**
     * 최종주문 내역 출력 메소드
     * 최종금액이 5만원 미만일경우 배송료 추가
     */
    public void calculateFinalPrice(List<ProductDto> finalOrders){
        DecimalFormat formatter = new DecimalFormat("###,###");
        BigDecimal price = BigDecimal.ZERO;
        System.out.println("주문 내역:");
        System.out.println("------------------------------------");
        for(ProductDto order : finalOrders) {
            System.out.printf("%s - %d개\n",order.getProductName(),order.getAmount());
            price = price.add(order.getPrice().multiply(new BigDecimal(order.getAmount())));
        }
        System.out.println("------------------------------------");
        System.out.println("주문 금액 : " + formatter.format(price) + "원 ");
        if(new BigDecimal(50000).compareTo(price) > 0) {
            System.out.println("배송비 : 2,500원");
            price = price.add(new BigDecimal(2500));
        }
        System.out.println("------------------------------------");
        System.out.println("지불 금액 : " + formatter.format(price) + "원");
        System.out.println("------------------------------------");

    }

}
