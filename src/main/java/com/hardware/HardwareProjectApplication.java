package com.hardware;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.hardware.gui.Clock;
import com.hardware.gui.MainLayout;
import com.hardware.helper.ConfigReader;
import com.hardware.helper.PISerialPortEventListener;
import com.hardware.model.PiStamp;
import com.hardware.piController.PiController;
import com.hardware.service.RxTxService;
import com.hardware.service.SpringService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Exchanger;

@Lazy
@SpringBootApplication
public class HardwareProjectApplication extends Application {


	private static final Logger log = LoggerFactory.getLogger(HardwareProjectApplication.class);
	private static ConfigurableApplicationContext applicationContext;

	@Autowired
	SpringService serverService;

	@Autowired
	PiController ctrl;


	private MainLayout mainLayout = new MainLayout();

	/**
	 * Starts our gui
	 * @param primaryStage the gui
	 * @throws Exception
     */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = new Stage(StageStyle.UNDECORATED);
		primaryStage.setScene(new Scene(mainLayout,480,320));
		Clock c = new Clock(mainLayout);
		c.clockLine(0,0,0);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	/**
	 * Initialize all objects that will be used
	 * @throws Exception
     */
	@Override
	public void init() throws Exception {

		//super.init();
		System.out.println("STARTING Hardware App");
		SpringApplication app = new SpringApplication(HardwareProjectApplication.class);
		app.setWebEnvironment(false);
		applicationContext = app.run();
		applicationContext.getAutowireCapableBeanFactory().autowireBean(app);


		serverService = new SpringService();
		RxTxService rxtxService = new RxTxService();
		PISerialPortEventListener listener = new PISerialPortEventListener();
		ConfigReader reader = new ConfigReader();

		ctrl = new PiController();
		ctrl.setConfigFile(reader);
		ctrl.setEventListener(listener);
		ctrl.setServerService(serverService);
		ctrl.setRxTxService(rxtxService);
		ctrl.setMainView(mainLayout);
		ctrl.init();


		//serverService.testSendingRFID();

	}

	/**
	 * Stopps the application
	 * @throws Exception
     */
	@Override
	public void stop() throws Exception {
		super.stop();
		applicationContext.close();
	}

	public static void main(String[] args) {
		launch(args);
	}


}
