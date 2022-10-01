package com.infoweaver.springtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Ruobing Shang 2022-09-01
 */

@EnableCaching
@SpringBootApplication
public class SpringTutorialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTutorialApplication.class, args);
    }
}
