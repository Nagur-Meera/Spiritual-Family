package com.spiritualfamily.backend.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotificationScheduler {

    @Scheduled(cron = "0 0 8 * * ?")
    public void morningNotification() {

        log.info(
                "Daily spiritual notification triggered"
        );
    }
}