package com.hardware.piController;

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
		log.info("running Init");
		rxTxService.initialize();
	}
}
