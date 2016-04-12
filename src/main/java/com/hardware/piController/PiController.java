package com.hardware.piController;

import com.hardware.gui.MainLayout;
import com.hardware.helper.PISerialPortEventListener;
import com.hardware.model.PiStamp;
import com.hardware.service.RxTxService;
import com.hardware.service.SpringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by jonatan on 2016-04-12.
 */
@Component
public class PiController {

	private static final Logger log = LoggerFactory.getLogger(PiController.class);

	private SpringService serverService;
	private RxTxService rxTxService;
    private PISerialPortEventListener listener;
	private MainLayout mainView;

	public PiController() {
		System.out.println("stared Pi controller");
	}

	public void getDataFromArduino(String rfidKey) {
		System.out.println("Got from pi: "+rfidKey);

	}

	public void setServerService(SpringService serverService) {
		this.serverService = serverService;
	}

	public void setRxTxService(RxTxService rxTxService) {
		this.rxTxService = rxTxService;
	}

	/**
	 * Setup services
	 */
	public void init() {
        listener.setController(this);
		log.info("running Init");
        rxTxService.setEventHandler(listener);
		rxTxService.initialize();
	}

    public void setEventListener(PISerialPortEventListener listener) {
        this.listener = listener;
    }

	public void setMainView(MainLayout mainView) {
		this.mainView = mainView;
	}

    public void sendToServer(String inputLine) {
        PiStamp stamp = serverService.sendRfid(inputLine);
        if(stamp != null){
            log.info("Sending to gui " + stamp.toString());
            mainView.setServerAnswer(stamp);
        }else{
            log.error("Failed to recive");
        }
    }
}
