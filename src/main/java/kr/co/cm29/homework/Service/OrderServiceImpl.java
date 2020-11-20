package kr.co.cm29.homework.Service;

import kr.co.cm29.homework.model.ProductDto;
import kr.co.cm29.homework.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository repository;


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
    @Override
    public BigDecimal findById(int productNumber) {
        return repository.findById(productNumber);
    }


}
