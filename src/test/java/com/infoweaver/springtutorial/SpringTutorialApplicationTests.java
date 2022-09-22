package com.infoweaver.springtutorial;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringTutorialApplicationTests {

    @BeforeAll
    static void beforeAll() {
        log.info("===Test Begin===");
    }

    @AfterAll
    static void afterAll() {
        log.info("===Test End===");
    }

    @Test
    void contextLoads() {
    }

}
