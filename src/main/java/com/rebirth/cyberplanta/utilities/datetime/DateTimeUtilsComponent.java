package com.rebirth.cyberplanta.utilities.datetime;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class DateTimeUtilsComponent {

    public LocalDateTime timestampToLocalDateTime(Long timestamp) {
        return TimestampUtils.timestampToLocalDateTime(timestamp);
    }

}
