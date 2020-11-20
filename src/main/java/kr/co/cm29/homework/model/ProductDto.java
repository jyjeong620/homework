package kr.co.cm29.homework.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ProductDto {

    private int ProductNumber;

    private String ProductName;

    private BigDecimal Price;

    private int Amount;
}
