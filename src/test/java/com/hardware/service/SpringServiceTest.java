package com.hardware.service;

import com.hardware.model.PiStamp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonatan on 2016-04-13.
 */
public class SpringServiceTest {

    private SpringService service = new SpringService();

    @Test
    public void testSendRfid() throws Exception {
        //an id that i know exist A448182B
        //is Einar Andersson
        Object test = service.sendRfid("A448182B");
        if(test instanceof PiStamp)
            System.out.println("true");
        else
            System.out.println("false");
    }
}