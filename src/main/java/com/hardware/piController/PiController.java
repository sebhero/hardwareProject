package com.hardware.piController;

import com.hardware.gui.MainLayout;
import com.hardware.helper.ConfigReader;
import com.hardware.helper.PISerialPortEventListener;
import com.hardware.model.PiStamp;
import com.hardware.model.RfidKey;
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
	private ConfigReader configFile;

	public PiController() {
		System.out.println("stared Pi controller");
	}

	/**
	 * Setup services
	 */
	public void init() {
		this.configFile.loadConfig();
		serverService.setIp(configFile.getIp());
		rxTxService.setCompPort(configFile.getCompPort());
		rxTxService.setPiPort(configFile.getPiPort());
		rxTxService.setUsesPi(configFile.isUsingPi());
		listener.setController(this);
		log.info("running Init");
		rxTxService.setEventHandler(listener);
		rxTxService.initialize();
	}

	/**
	 * Sets up serverlink
	 * @param serverService the serverclass
     */
	public void setServerService(SpringService serverService) {
		this.serverService = serverService;
	}

	/**
	 * Sets up the rxtxservicelink
	 * @param rxTxService the input
     */
	public void setRxTxService(RxTxService rxTxService) {
		this.rxTxService = rxTxService;
	}

	/**
	 * Sets upp eventlistern
	 * @param listener our eventlistener
     */
    public void setEventListener(PISerialPortEventListener listener) {
        this.listener = listener;
    }

	/**
	 * Sets the mainview
	 * @param mainView we want to use
     */
	public void setMainView(MainLayout mainView) {
		this.mainView = mainView;
	}

	/**
	 * This method will send a string to servern,
	 * then if the stamp isn´t null we will transfer the stamp to the gui.
	 *
	 * @param key rfid
     */
    public void sendToServer(RfidKey key) {
        PiStamp stamp = serverService.sendRfid(key);
		try{
        if(stamp != null){
            log.info("Sending to gui " + stamp.toString());
            mainView.setServerAnswer(stamp);
        }else{
            log.error("Failed to recieve");
			mainView.setConnectionFail("Connection failed");
        }
     }catch(IllegalStateException e){
		System.out.println("Error!");

	 }
	}
	public void testPiUpdate(String str){
		mainView.setConnectionFail(str);
	}
	public void corruptReading() {
		//Todo send error message to gui
	//	mainView.setErrorMessage("Corrupt CardId try again");
	}

	public void setConfigFile(ConfigReader configFile) {
		this.configFile = configFile;
	}
}
