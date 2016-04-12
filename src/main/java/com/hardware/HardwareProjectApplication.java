package com.hardware;

import com.hardware.model.PiStamp;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HardwareProjectApplication extends Application {

	private static final Logger log = LoggerFactory.getLogger(HardwareProjectApplication.class);
	private static ConfigurableApplicationContext applicationContext;

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
		System.out.println("STARTING FUCKING SERVER");
		SpringApplication app = new SpringApplication(HardwareProjectApplication.class);
		app.setWebEnvironment(false);
		applicationContext = app.run();
		applicationContext.getAutowireCapableBeanFactory().autowireBean(app);



		///do A SERVER CALL
		RestTemplate restTemplate = new RestTemplate();
		//// TODO: 2016-04-12 Change Server IP
		PiStamp quote = restTemplate.getForObject("http://192.168.1.51:8080/pi/247615E", PiStamp.class);
		log.info(quote.toString());

	}


}
