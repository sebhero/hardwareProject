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

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Johnatan S, Jonatan F
 * Class created by Jonatan F, Modified by Johnatan S
 * This class handles the controllers.
 */
@Component
public class PiController {

	private static final Logger log = LoggerFactory.getLogger(PiController.class);
	private SpringService serverService;
	private RxTxService rxTxService;
    private PISerialPortEventListener listener;
	private MainLayout mainView;
	private ConfigReader configFile;
	public static volatile int status = -1;
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
		rxTxService.setCtrl(this);
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
	 * This method will send a string to servern.
	 * Then, depending on what status is applied to it, different messages are sent to the gui.
	 * If no status is applied (meaning no problem has occurred) a regular stamp with time and identification will be
	 * sent to the GUI
	 *
	 * @param key rfid
     */
    public void sendToServer(RfidKey key) {
		PiStamp stamp = serverService.sendRfid(key);
		try{
        if(stamp.getPiStatus()==null){
				log.info("Sending to gui " + stamp.toString());
				setStatus(0);
				mainView.setServerAnswer(stamp);

        }else{
			System.out.println(stamp.getPiStatus());
			if(stamp.getPiStatus().equals("400 Bad Request")){
				mainView.setFailMessage("User not found");
			}
			else if(stamp.getPiStatus().equals("403 Forbidden")){
				mainView.setFailMessage("Card disabled");
			}
			else if(stamp.getPiStatus().equals("423 Locked")){
				mainView.setFailMessage("Account is locked");
			}
			else if(stamp.getPiStatus().equals("401 Unauthorized")){
				mainView.setFailMessage("Unauthorized user");
			}
			else {
				log.error("Failed to recieve");
				mainView.setFailMessage("Connection failed");
			}
			setStatus(1);

        }
     }catch(IllegalStateException e){
		System.out.println("Error!");

	 }
	}
	public void corruptReading() {
		//Todo send error message to gui
	}

	/**
	 * Sets the status of the card. If it's good to go or if a problem occured. This status is then
	 * sent to the arduino which in turn displays the status with red and green lamp.
	 *
	 * @param status the status that is sent back to the arduino
     */
	public void setStatus(int status){
		this.status = status;
	}

	/**
	 * Gets the status that is to be sent to the arduino.
	 *
	 * @return returns the status
     */
	public int getStatus(){
		return status;
	}

	public void setConfigFile(ConfigReader configFile) {
		this.configFile = configFile;
	}
}
