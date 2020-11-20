package kr.co.cm29.homework.common;

import kr.co.cm29.homework.Service.OrderService;
import kr.co.cm29.homework.Service.OrderServiceImpl;
import kr.co.cm29.homework.controller.OrderController;
import kr.co.cm29.homework.model.ProductDto;
import kr.co.cm29.homework.repository.OrderRepository;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Order {
    OrderRepository orderRepository;
    OrderService orderService;
    OrderController orderController;
    List<ProductDto> productList;

    public Order() throws Exception{
        this.productList = new DataSetting().setting();
        this.orderRepository = new OrderRepository(this.productList);
        this.orderService = new OrderServiceImpl(this.orderRepository);
        this.orderController = new OrderController(this.orderService);
    }

    public void run(){
        boolean shopRunning = true;
        Scanner scan = new Scanner(System.in);

        while(shopRunning){
            System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
            String input = scan.nextLine();
            if(input.equals("o")){
                orderController.printAll();

            } else if(input.equals("q")){
                shopRunning = false;
                System.out.println("고객님의 주문 감사합니다.");
            } else {
                System.out.println("잘못 입력하셨습니다.");
            }

        }
    }
}
