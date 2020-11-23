package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.Service.OrderService;
import kr.co.cm29.homework.Service.OrderServiceImpl;
import kr.co.cm29.homework.common.DataSetting;
import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;
import kr.co.cm29.homework.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class OrderControllerTest  {

    OrderRepository orderRepository;
    OrderService orderService;
    OrderController orderController;
    List<ProductDto> productList;

    @BeforeEach
    void beforeEach() throws IOException {
        this.productList = new DataSetting().setting();
        this.orderRepository = new OrderRepository(this.productList);
        this.orderService = new OrderServiceImpl(this.orderRepository);
        this.orderController = new OrderController(this.orderService);
    }

    @Test
    @DisplayName("모든 상품 출력")
    void printAll() {
        orderController.printAll();
    }

    @Test
    @DisplayName("상품번호와 수량을 입력하여 주문")
    void calculatePrice(){
        //given
        List<ProductDto> orders = new ArrayList<>();
        ProductDto order = new ProductDto();
        order.setProductNumber(778422);
        order.setAmount(1);
        orders.add(order);

        try {
            orderController.calculateFinalPrice(orderController.calculateOrders(orders));
        } catch (SoldOutException e){

        }
    }

    @Test
    void multiOrder() throws InterruptedException {
//        calculatePrice();
        MultiThread[] mt = new MultiThread[10];
        for(int i = 0 ; i < 10 ; i++) {
            mt[i] = new MultiThread();
            mt[i].start();
//            mt[i].sleep(10);
        }
    }

    class MultiThread extends Thread{
        @Override
        public void run() {
            calculatePrice();
        }
    }

    @Test
    @DisplayName("최종금액이 50000원 이하일경우 배송비 추가")
    void downFinalPrice() {
        //given
        List<ProductDto> orders = new ArrayList<>();
        ProductDto order = new ProductDto();
        order.setProductName("상품1");
        order.setAmount(2);
        order.setPrice(new BigDecimal(4000));
        orders.add(order);

        //when then
        orderController.calculateFinalPrice(orders);
    }

    @Test
    @DisplayName("최종금액이 50000원 이상일경우")
    void upFinalPrice() {
        //given
        List<ProductDto> orders = new ArrayList<>();
        ProductDto order = new ProductDto();
        order.setProductName("상품1");
        order.setAmount(2);
        order.setPrice(new BigDecimal(40000));
        orders.add(order);

        //when then
        orderController.calculateFinalPrice(orders);
    }
}