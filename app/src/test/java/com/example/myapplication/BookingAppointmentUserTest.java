package com.example.myapplication;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class BookingAppointmentUserTest {

    @Test
    public void calendarYearTest() {
        int year = 2002;
        int month = 2;
        int day = 2;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        Calendar output = bookingAppointmentUser.calendarCreate(year, month, day);

        assertEquals(2002, output.get(Calendar.YEAR));
    }

    @Test
    public void calendarMonthTest() {
        int year = 2002;
        int month = 2;
        int day = 3;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        Calendar output = bookingAppointmentUser.calendarCreate(year, month, day);

        assertEquals(2, output.get(Calendar.MONTH));
    }

    @Test
    public void calendarDayTest() {
        int year = 2002;
        int month = 2;
        int day = 3;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        Calendar output = bookingAppointmentUser.calendarCreate(year, month, day);

        assertEquals(3, output.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void calendarZeroTest() {
        int year = 0;
        int month = 0;
        int day = 0;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        Calendar output = bookingAppointmentUser.calendarCreate(year, month, day);

        assertEquals(31, output.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void calendarNotYearTest() {
        int year = 2019;
        int month = 12;
        int day = 03;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        Calendar output = bookingAppointmentUser.calendarCreate(year, month, day);

        assertNotEquals(2018, output.get(Calendar.YEAR));
    }


    @Test
    public void dayOfWeekStandard() {
        int year = 2002;
        int month = 2;
        int day = 2;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        String output = bookingAppointmentUser.dayOfWeek(year, month, day);

        assertEquals("2002-3-2", output);
    }

    @Test
    public void dayOfWeekNegative() {
        int year = -1000;
        int month = 2;
        int day = 2;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        String output = bookingAppointmentUser.dayOfWeek(year, month, day);

        assertEquals("-1000-3-2", output);
    }

    @Test
    public void dayOfWeekZero() {
        int year = 0;
        int month = 0;
        int day = 0;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        String output = bookingAppointmentUser.dayOfWeek(year, month, day);

        assertEquals("0-1-0", output);
    }

    @Test
    public void dayOfWeekDash() {
        int year = 0;
        int month = 0;
        int day = 0;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        String output = bookingAppointmentUser.dayOfWeek(year, month, day);

        assertNotEquals("010", output);
    }

    @Test
    public void dayOfWeekOneOff() {
        int year = 2002;
        int month = 2;
        int day = 2;

        BookingAppointmentUser bookingAppointmentUser = new BookingAppointmentUser();
        String output = bookingAppointmentUser.dayOfWeek(year, month, day);

        assertNotEquals("2002-2-2", output);
    }
}