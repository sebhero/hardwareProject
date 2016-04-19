package com.hardware.piController;

import com.hardware.gui.MainLayout;
import com.hardware.helper.PISerialPortEventListener;
import com.hardware.service.RxTxService;
import com.hardware.service.SpringService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonatan on 2016-04-15.
 */
public class PiControllerTest {

    private SpringService serverService;
    private RxTxService rxTxService;
    private PISerialPortEventListener listener;
    private MainLayout mainView;
    private PiController controller;

    @Before
    public void setUp() throws Exception {
        controller.setEventListener(listener);
        controller.setMainView(mainView);
        controller.setRxTxService(rxTxService);
        controller.setServerService(serverService);
        controller.init();
    }

    @Test
    public void testSendToServer() throws Exception {

    }

    @Test
    public void testCorruptReading() throws Exception {

    }
}