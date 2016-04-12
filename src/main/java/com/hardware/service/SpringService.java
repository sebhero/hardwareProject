package com.hardware.service;

import com.hardware.model.PiStamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by seb on 2016-04-12.
 */

@Component
public class SpringService {

	private static final Logger log = LoggerFactory.getLogger(SpringService.class);


	public SpringService() {
	}

	public void testSendingRFID() {
///do A SERVER CALL
		RestTemplate restTemplate = new RestTemplate();
		//// TODO: 2016-04-12 Change Server IP
		try {
			PiStamp quote = restTemplate.getForObject("http://192.168.1.51:8080/pi/247615E", PiStamp.class);
			//PiStamp quote = restTemplate.getForObject("http://localhost:8080/pi/247615E", PiStamp.class);
			System.out.println("GOT Answer from server");
			log.info(quote.toString());
		}catch (Exception e){
			log.info("Error starting connecting to server");
		}
	}

	public PiStamp sendRfid(String inputLine) {

        log.info("Sending data to server " + inputLine);
		///do A SERVER CALL
		RestTemplate restTemplate = new RestTemplate();
		//// TODO: 2016-04-12 Change Server IP
		try {
			PiStamp stamp = restTemplate.getForObject("http://192.168.1.51:8080/pi/" + inputLine, PiStamp.class);
			//PiStamp quote = restTemplate.getForObject("http://localhost:8080/pi/247615E", PiStamp.class);
			System.out.println("GOT Answear from server");
			log.info(stamp.toString());
			return stamp;
		}catch (Exception e){
			log.info("Error starting coonecting to server");
		}
		return null;
	}
}
