package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.exception.SoldOutException;
import kr.co.cm29.homework.model.ProductDto;

import java.util.List;

public interface OrderController {
    void printAll();
    void findProductNumber() throws SoldOutException;
    void calculateOrders(List<ProductDto> orders) throws SoldOutException;
    void printFinalProduct(List<ProductDto> finalOrders);
}
