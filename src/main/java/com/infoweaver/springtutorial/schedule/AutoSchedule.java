package com.infoweaver.springtutorial.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Ruobing Shang 2024-01-05 15:17
 */
@Slf4j
@Component
public class AutoSchedule {
    /**
     * 每天00点30触发一次
     */
    @Scheduled(cron = "0 30 0 * * ?")
    public void executeDailyTask() {
        log.info("定时任务开始时间:" + LocalDateTime.now());
        log.info("定时任务结束时间:" + LocalDateTime.now());
    }
}
