package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.common.OrderRunner;
import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class OrderControllerTest  {
    static OrderRunner order;

    @BeforeEach
    void BeforeAll() throws IOException {
        order = OrderRunner.getInstance();

    }

    @Test
    @DisplayName("모든 상품 출력")
    void printAll() {
//        orderController.printAll();
        order.getOrderController().printAll();
    }

    @Test
    @DisplayName("상품번호와 수량을 입력하여 주문")
    void calculatePrice() {
        //given
        List<ProductDto> orders = new ArrayList<>();
        ProductDto productOrder = new ProductDto();
        productOrder.setProductNumber(778422);
        productOrder.setAmount(1);
        orders.add(productOrder);

        try {
            order.getOrderController().calculateOrders(orders);

        } catch (SoldOutException e){

        }
    }

    @Test
    @DisplayName("멀티 쓰레드 환경에서 SoldOutException 처리 확인")
    void multiOrder() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        for (int i = 0; i < numberOfThreads; i++) {
            service.execute(() -> {
                calculatePrice();
                latch.countDown();
            });
        }
        latch.await();
    }

    @Test
    @DisplayName("최종금액이 50000원 이하일경우 배송비 추가")
    void downFinalPrice() {
        //given
        List<ProductDto> orders = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setProductName("상품1");
        productDto.setAmount(2);
        productDto.setPrice(new BigDecimal(4000));
        orders.add(productDto);

        //when then
        order.getOrderController().printFinalProduct(orders);
    }

    @Test
    @DisplayName("최종금액이 50000원 이상일경우")
    void upFinalPrice() {
        //given
        List<ProductDto> orders = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setProductName("상품1");
        productDto.setAmount(2);
        productDto.setPrice(new BigDecimal(40000));
        orders.add(productDto);

        //when then
        order.getOrderController().printFinalProduct(orders);
    }
}