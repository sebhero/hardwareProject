package com.hardware.model;


import org.junit.Assert;

import java.util.Calendar;

/**
 * Created by jonatan on 2016-04-13.
 */
public class PiStampTest {

    private PiStamp stamp;

    @org.junit.Before
    public void setUp() throws Exception {
        stamp = new PiStamp();
    }

    @org.junit.Test
    public void testGetLastName() throws Exception {
        //given
        stamp.setLastName("Lennartson");
        //when
        String lastName = stamp.getLastName();
        //then
        Assert.assertEquals(lastName,"Lennartson");
    }

    @org.junit.Test
    public void testSetLastName() throws Exception {
        //given
        stamp.setLastName("Svensson");
        //when
        String lastName = stamp.getLastName();
        //then
        Assert.assertEquals(lastName,"Svensson");
    }

    @org.junit.Test
    public void testGetFirstName() throws Exception {
        //given
        stamp.setFirstName("Glenn");
        //when
        String firstName = stamp.getFirstName();
        //then
        Assert.assertEquals(firstName,"Glenn");
    }

    @org.junit.Test
    public void testSetFirstName() throws Exception {        //given
        //given
        stamp.setFirstName("Åke");
        //when
        String firstName = stamp.getFirstName();
        //then
        Assert.assertEquals(firstName,"Åke");
    }

    @org.junit.Test
    public void testIsCheckIn() throws Exception {
        //given
        stamp.setCheckIn(true);
        //when
        boolean check = stamp.isCheckIn();
        //then
        Assert.assertEquals(check,true);
    }

    @org.junit.Test
    public void testSetCheckIn() throws Exception {
        //given
        stamp.setCheckIn(false);
        //when
        boolean check = stamp.isCheckIn();
        //then
        Assert.assertEquals(check,false);
    }

    @org.junit.Test
    public void testGetDate() throws Exception {
        //given
        Calendar date = Calendar.getInstance();
        stamp.setDate(date);
        //when
        Calendar get = stamp.getDate();
        //then
        Assert.assertEquals(get,date);
    }

    @org.junit.Test
    public void testSetDate() throws Exception {
        //given
        Calendar date = Calendar.getInstance();
        stamp.setDate(date);
        //when
        Calendar get = stamp.getDate();
        //then
        Assert.assertEquals(get,date);
    }

    @org.junit.Test
    public void testToString() throws Exception {

    }
}