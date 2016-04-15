package com.hardware.model;

import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonatan on 2016-04-13.
 */
public class RfidKeyTest {

    private RfidKey key = new RfidKey("adc123");
    @Test
    public void testGetId() throws Exception {
        //given
        //when
        String get = key.getId();
        //then
        Assert.assertEquals(get,"adc123");
    }

    @Test
    public void testSetId() throws Exception {
        //given
        key.setId("adddc12323");
        //when
        String get = key.getId();
        //then
        Assert.assertEquals(get,"adddc12323");
    }
}