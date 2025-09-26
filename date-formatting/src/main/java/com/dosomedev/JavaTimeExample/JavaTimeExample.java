package com.dosomedev.JavaTimeExample;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JavaTimeExample implements Runnable {
    @Override
    public void run() {
        IO.println("Dates using java.time package:");

        // Get current date and time.
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        // Print the dates.
        IO.println("Local date:                            "
                           + localDate);
        IO.println("Local time:                            "
                           + localTime);
        IO.println("Local date and time:                   "
                           + localDateTime);

        IO.println("Local date formatted:                  "
                           + localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        IO.println("Local time formatted:                  "
                           + localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        IO.println("Local date and time formatted:         "
                           + localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

        // Set a specific date.
        localDate = LocalDate.of(2022, 02, 22);
        localTime = LocalTime.of(12, 23, 51);
        localDateTime = LocalDateTime.of(2022, 02, 22, 12, 23, 51);

        // Print the dates.
        IO.println("Local changed date:                    "
                           + localDate);
        IO.println("Local changed time:                    "
                           + localTime);
        IO.println("Local changed date and time:           "
                           + localDateTime);

        IO.println("Local changed date formatted:          "
                           + localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        IO.println("Local changed time formatted:          "
                           + localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        IO.println("Local changed date and time formatted: "
                           + localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

        // Add days, months, hours.
        LocalDate tomorrow = localDate.plusDays(1);
        LocalDate lastMonth = localDate.minusMonths(1);
        LocalTime oneHourLater = localTime.plusHours(1);
        LocalDateTime oneDayAgo = localDateTime.minusDays(1);

        // Print the dates.
        IO.println("Tomorrow:                              "
                           + tomorrow.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        IO.println("Last month:                            "
                           + lastMonth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        IO.println("One hour later:                        "
                           + oneHourLater.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        IO.println("One day ago:                           "
                           + oneDayAgo.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));

        IO.println();
    }
}
