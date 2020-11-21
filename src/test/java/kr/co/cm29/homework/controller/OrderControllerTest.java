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
    void findProductNumber() {
        try {
            orderController.findProductNumber();
        } catch (SoldOutException e){

        }
    }

    @Test
    void calculatePrice(){
        try {
            System.out.println("최종금액 : "+ orderController.calculatePrice(517643,2));
        } catch (SoldOutException e){

        }
    }
    @Test
    @DisplayName("최종금액이 50000원 이하일경우 배송비 추가")
    void downFinalPrice() {
        //given
        BigDecimal price = new BigDecimal(40000);
        orderController.calculateFinalPrice(price);
    }

    @Test
    @DisplayName("최종금액이 50000원 이상일경우")
    void upFinalPrice() {
        //given
        BigDecimal price = new BigDecimal(70000);
        orderController.calculateFinalPrice(price);
    }
}