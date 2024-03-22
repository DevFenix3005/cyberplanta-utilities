package com.rebirth.cyberplanta.utilities.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

class TimestampUtils {

    private TimestampUtils() {
    }

    static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        return Optional.ofNullable(timestamp)
                .map(ts -> LocalDateTime.ofInstant(Instant.ofEpochMilli(ts), ZoneId.systemDefault()))
                .orElse(null);

    }

}
