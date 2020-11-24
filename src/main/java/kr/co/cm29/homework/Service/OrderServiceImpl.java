package kr.co.cm29.homework.Service;

import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;
import kr.co.cm29.homework.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    private static OrderServiceImpl instance;
    private final OrderRepository repository;

    private OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    public synchronized static OrderServiceImpl getInstance(OrderRepository repository){
        if(OrderServiceImpl.instance == null){
            OrderServiceImpl.instance = new OrderServiceImpl(repository);
        }
        return OrderServiceImpl.instance;
    }

    /**
     * Product 데이터를 모두 가져오는 메소드
     * @return List<ProductDto>
     */
    @Override
    public List<ProductDto> findAllProduct() {
        return repository.findAll();
    }

    /**
     * 해당 상품번호의 판매가격을 가져오는 메소드
     * @param productNumber 상품번호
     * @return 판매가격
     */
    public BigDecimal findByPrice(int productNumber) {
        return repository.findByPrice(productNumber);
    }

    /**
     * 해당 상품번호의 상품명을 가져오는 메소드
     * @param productNumber 상품번호
     * @return 상품명
     */
    public String findByProductName(int productNumber){
        return repository.findByProductName(productNumber);
    }

    /**
     * 상품번호와 수량을 받아와 해당 상품의 재료수 마이너스
     */
    @Override
    public void findByAmount(int productNumber, int amount) throws SoldOutException {
        repository.subAmount(productNumber,amount);
    }
}
