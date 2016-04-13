package com.hardware;

import com.hardware.gui.MainLayout;
import com.hardware.helper.PISerialPortEventListener;
import com.hardware.model.PiStamp;
import com.hardware.piController.PiController;
import com.hardware.service.RxTxService;
import com.hardware.service.SpringService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

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
		primaryStage.setScene(new Scene(mainLayout,420,380));
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

		ctrl = new PiController();
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
