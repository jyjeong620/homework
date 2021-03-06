package kr.co.cm29.homework.common;


import com.opencsv.CSVReader;
import kr.co.cm29.homework.model.ProductDto;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class DataSetting {

    private static DataSetting instance;

    private DataSetting(){}

    public synchronized static DataSetting getInstance(){
        if(DataSetting.instance == null){
            instance = new DataSetting();
        }
        return instance;
    }

    /**
     * 파일을 읽어와 상품 데이터를 세팅
     */
    public List<ProductDto> setting() throws IOException {
        List<ProductDto> product = new ArrayList<>();
        File file = new File("");
        String filePath = file.getAbsolutePath();
        String fileName = "백엔드 과제 상품 데이터.csv";
        log.debug("파일경로 : " + filePath+ "\\" +fileName);

        CSVReader reader = new CSVReader(new FileReader(filePath+ "\\" +fileName));
        String[] nextLine;
        reader.readNext();
        while ((nextLine = reader.readNext()) != null) {
            ProductDto dto = new ProductDto();
            if(nextLine[0] != null) {
                dto.setProductNumber(Integer.parseInt(nextLine[0]));
                dto.setProductName(nextLine[1]);
                dto.setPrice(new BigDecimal(nextLine[2]));
                dto.setAmount(Integer.parseInt(nextLine[3]));
            }
            product.add(dto);
        }
        Collections.sort(product);

        return product;
    }
}
