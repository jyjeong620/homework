package kr.co.cm29.homework.common;

import kr.co.cm29.homework.model.ProductDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DataSettingTest {

    @Test
    void setting() {
        //given
        DataSetting dataSetting = new DataSetting();
        List<ProductDto> products = new ArrayList<>();

        //when
        try {
            products =  dataSetting.setting();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //then
        System.out.printf("%s\t\t%-50s%s\t\t%s\n","상품번호","상품명","판매가격","재고수");
        for(ProductDto data : products){
            System.out.printf("%s\t\t%-50s%s\t\t%s\n",data.getProductNumber(),data.getProductName(),data.getPrice(),data.getAmount());
        }

    }
}