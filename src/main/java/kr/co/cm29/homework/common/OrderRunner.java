package kr.co.cm29.homework.common;

import kr.co.cm29.homework.Service.OrderService;
import kr.co.cm29.homework.Service.OrderServiceImpl;
import kr.co.cm29.homework.controller.OrderControllerImpl;
import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;
import kr.co.cm29.homework.repository.OrderRepository;
import lombok.Getter;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Getter
public class OrderRunner {
    private static OrderRunner instance;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final OrderControllerImpl orderController;
    private final List<ProductDto> productList;

    private OrderRunner() throws IOException{
        this.productList = DataSetting.getInstance().setting();
        this.orderRepository = OrderRepository.getInstance(this.productList);
        this.orderService = OrderServiceImpl.getInstance(this.orderRepository);
        this.orderController = OrderControllerImpl.getInstance(this.orderService);
    }

    public synchronized static OrderRunner getInstance() throws IOException{
        if(OrderRunner.instance == null){
            OrderRunner.instance = new OrderRunner();
        }
        return instance;
    }

    /**
     * 서비스 시작
     */
    public void run(){
        boolean shopRunning = true;
        Scanner scan = new Scanner(System.in);

        while(shopRunning){
            System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
            String input = scan.nextLine();

            if(input.equalsIgnoreCase("o") || input.equalsIgnoreCase("order")){
                orderController.printAll();     //전체 상품목록 리스트 출력
                try {
                    orderController.findProductNumber();
                } catch (SoldOutException e) {
//                    e.printStackTrace();
                }
            } else if(input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit")){
                shopRunning = false;
                System.out.println("고객님의 주문 감사합니다.");
            } else {
                System.out.println("잘못 입력하셨습니다.");
            }

        }
    }
}
