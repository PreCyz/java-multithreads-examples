package com.dosomedev.FutureWorkdayExample;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FutureWorkdayExample implements Runnable {
    @Override
    public void run() {
        IO.println("Future workday with respect to weekends and holidays (Zürich / Switzerland):");

        List<LocalDate> holidays = new ArrayList<>();
        holidays.add(LocalDate.of(2024, 1, 1));   // Public holiday: Neujahr
        holidays.add(LocalDate.of(2024, 1, 2));   // Public holiday: Berchtoldstag
        holidays.add(LocalDate.of(2024, 3, 29));  // Public holiday: Karfreitag
        holidays.add(LocalDate.of(2024, 4, 1));   // Public holiday: Ostermontag
        holidays.add(LocalDate.of(2024, 5, 1));   // Public holiday: Tag der Arbeit
        holidays.add(LocalDate.of(2024, 5, 9));   // Public holiday: Christi Himmelfahrt
        holidays.add(LocalDate.of(2024, 5, 20));  // Public holiday: Pfingsmontag
        holidays.add(LocalDate.of(2024, 8, 1));   // Public holiday: Bundesfeiertag
        holidays.add(LocalDate.of(2024, 9, 15));  // Public holiday: Eidgenössischer Dank-, Buss- und Bettag
        holidays.add(LocalDate.of(2024, 12, 25)); // Public holiday: Erster Weihnachtstag
        holidays.add(LocalDate.of(2024, 12, 26)); // Public holiday: Stefanitag

        FutureHoliday futureHoliday = new FutureHoliday();
        futureHoliday.addHolidays(holidays);

        LocalDate startDate = LocalDate.of(2024, 5, 6);
        LocalDate futureWorkdayA = futureHoliday.getFutureWorkday(startDate, 1);
        LocalDate futureWorkdayB = futureHoliday.getFutureWorkday(startDate, 2);
        LocalDate futureWorkdayC = futureHoliday.getFutureWorkday(startDate, 3);
        LocalDate futureWorkdayD = futureHoliday.getFutureWorkday(startDate, 4);
        LocalDate futureWorkdayE = futureHoliday.getFutureWorkday(startDate, 5);

        // Format date.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        IO.println("Start date:       " + startDate.format(formatter));
        IO.println("Future Workday A: " + futureWorkdayA.format(formatter)
                           + " (Distance: " + FutureHoliday.getDayDistance(startDate, futureWorkdayA) + ")");
        IO.println("Future Workday B: " + futureWorkdayB.format(formatter)
                           + " (Distance: " + FutureHoliday.getDayDistance(startDate, futureWorkdayB) + ")");
        IO.println("Future Workday C: " + futureWorkdayC.format(formatter)
                           + " (Distance: " + FutureHoliday.getDayDistance(startDate, futureWorkdayC) + ")");
        IO.println("Future Workday D: " + futureWorkdayD.format(formatter)
                           + " (Distance: " + FutureHoliday.getDayDistance(startDate, futureWorkdayD) + ")");
        IO.println("Future Workday E: " + futureWorkdayE.format(formatter)
                           + " (Distance: " + FutureHoliday.getDayDistance(startDate, futureWorkdayE) + ")");

        IO.println();
    }
}
