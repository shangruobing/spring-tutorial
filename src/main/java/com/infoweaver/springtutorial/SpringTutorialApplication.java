package com.infoweaver.springtutorial;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ruobing Shang 2022-09-01
 */

@SpringBootApplication
@MapperScan
public class SpringTutorialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTutorialApplication.class, args);
    }

}
