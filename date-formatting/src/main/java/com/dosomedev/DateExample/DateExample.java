package com.dosomedev.DateExample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExample implements Runnable {
    @Override
    public void run() {
        IO.println("Dates using Date class:");

        // Create Date object.
        Date currentDate = new Date();

        /*
         * Format Date using SimpledateFormat.
         * yyyy: Year with century.
         * MM:   Month (01-12).
         * dd:   Day of the month (01-31).
         * HH:   Hour in 24-hour format (00-23).
         * mm:   Minute (00-59).
         * ss:   Second (00-59).
         */
        SimpleDateFormat formatterA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatterB = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatterC = new SimpleDateFormat("HH:mm:ss");
        String formattedDateA = formatterA.format(currentDate);
        String formattedDateB = formatterB.format(currentDate);
        String formattedDateC = formatterC.format(currentDate);

        // Print the dates.
        IO.println("Current date raw:           " + currentDate);
        IO.println("Current date formatted (A): " + formattedDateA);
        IO.println("Current date formatted (B): " + formattedDateB);
        IO.println("Current date formatted (C): " + formattedDateC);

        IO.println();
    }
}
