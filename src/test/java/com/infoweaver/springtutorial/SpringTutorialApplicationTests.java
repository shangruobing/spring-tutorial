package com.infoweaver.springtutorial;

import com.infoweaver.springtutorial.service.impl.ReceiptServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringTutorialApplicationTests {
    @Autowired
    private ReceiptServiceImpl receiptService;
    @Test
    void contextLoads() {
        System.out.println("Test------------BEGIN");
        System.out.println(receiptService.listReceiptVos());
        System.out.println("Test------------END");
    }

}
