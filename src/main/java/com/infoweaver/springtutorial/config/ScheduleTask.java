package com.infoweaver.springtutorial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @author Ruobing Shang 2022-10-01 22:27
 */
@Configuration
@EnableScheduling
public class ScheduleTask {
    /**
     * Annotation Scheduled(fixedRate = 1000 * 60 * 3)
     * ms * s * min * hour
     */
    @Scheduled(fixedRate = 1000 * 60 * 3)
    private void configureTasks() {
        System.err.println("Executing a static scheduled task: " + LocalDateTime.now());
    }
}
