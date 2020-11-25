package kr.co.cm29.homework.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ProductDto implements Comparable<ProductDto> {

    private int ProductNumber;  //상품번호

    private String ProductName; //상품이름

    private BigDecimal Price;   //상품가격

    private int Amount;         //재고수량

    @Override
    public int compareTo(ProductDto o) {
        if(this.ProductNumber < o.getProductNumber()){
            return 1;
        } else if(this.ProductNumber > o.getProductNumber()){
            return -1;
        }
        return 0;
    }
}
