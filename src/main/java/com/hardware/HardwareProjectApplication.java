package com.hardware;

import com.hardware.model.PiStamp;
import com.hardware.piController.PiController;
import com.hardware.service.RxTxService;
import com.hardware.service.SpringService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HardwareProjectApplication extends Application {

	private static final Logger log = LoggerFactory.getLogger(HardwareProjectApplication.class);
	private static ConfigurableApplicationContext applicationContext;

	@Autowired
	SpringService serverService;

	@Autowired
	PiController ctrl;

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		//		primaryStage.setScene(new Scene(mainLayout));
		primaryStage.show();
	}

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

		ctrl.setServerService(serverService);
		ctrl.setRxTxService(rxtxService);

		ctrl.init();
		//serverService.testSendingRFID();




	}


}
