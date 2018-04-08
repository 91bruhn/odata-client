////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 06.04.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public class AAAA {

    public static void main(String[] ag) {
        String da = "12.12.2018";
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(da, formatter);

        System.out.println(date.getYear());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getMonth());

    }
    //  Abflugszeit von der verbindung hier mit rein
    //    private static Date conv(BSONObject bsonObject, String value, LocalTime departureTime) {
    //        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSSSSS");
    //        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
    //
    //    }

    //    private static Date convertStringAsDateToDate(BSONObject bsonObject, String value) {//2 methhoden
    //        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    //        final LocalDate date = LocalDate.parse(convertToString(bsonObject, value), formatter);
    //        final LocalDateConverter ldc = new LocalDateConverter();
    //
    //        return (Date) ldc.encode(date);
    //    }
    //
    //    private static Date convertToDate(BSONObject bsonObject, String value) {
    //        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    //        final LocalDate date = LocalDate.parse(convertToString(bsonObject, value), formatter);
    //        final LocalTime time = LocalTime.of(createRandomNumber(0, 24), createRandomNumber(0, 60), 0);
    //        final LocalDateTime ldt = LocalDateTime.parse(convertToString(bsonObject, value), formatter);
    //        final LocalDateTime ldt2 = LocalDateTime.of(date, time);
    //        final LocalDateTimeConverter ldtc = new LocalDateTimeConverter();
    //        final LocalDateConverter ldc = new LocalDateConverter();
    //
    //        return (Date) ldtc.encode(ldt);
    //    }
}
