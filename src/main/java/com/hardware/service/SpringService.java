package com.hardware.service;

import com.hardware.gui.MainLayout;
import com.hardware.model.PiStamp;
import com.hardware.model.RfidKey;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;

/**
 * Created by seb on 2016-04-12.
 */

@Component
public class SpringService {

	private String ip = "http://projektessence.se/api/pi/";
	private static final Logger log = LoggerFactory.getLogger(SpringService.class);


	public SpringService() {
	}
	// TODO a test remove later!
	public void testSendingRFID() {
///do A SERVER CALL
		RestTemplate restTemplate = new RestTemplate();
		//// TODO: 2016-04-12 Change Server IP
		try {
			//på eduroam
			//PiStamp quote = restTemplate.getForObject("http://172.16.2.12:44344//pi/247615E", PiStamp.class);
			PiStamp quote = restTemplate.getForObject("http://192.168.1.51:8080/pi/247615E", PiStamp.class);
			//PiStamp quote = restTemplate.getForObject("http://192.168.1.51:8080/pi/247615E", PiStamp.class);
			//PiStamp quote = restTemplate.getForObject("http://localhost:8080/pi/247615E", PiStamp.class);
			System.out.println("GOT Answer from server");
			log.info(quote.toString());
		}catch (Exception e){
			log.info("Error starting connecting to server");
		}
	}

	/**
	 * Still method will send the rfid to the server,
	 * then it will return the answer as an piStamp
	 * @param key rfid
	 * @return pistamp
     */
	public PiStamp sendRfid(RfidKey key) {

        log.info("Sending data to server " + key.getId());
		///do A SERVER CALL
		String plainCreds = "piUser:pass";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);

		HttpEntity<String> request = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
		requestFactory.setReadTimeout(3000);
		requestFactory.setConnectTimeout(3000);
		//// TODO: 2016-04-12 Change Server IP
		try{
			ResponseEntity<PiStamp> response = restTemplate.exchange("https://projektessence.se/api/pi/" + key.getId(), HttpMethod.GET, request, PiStamp.class);
			PiStamp piStamp = response.getBody();
			//PiStamp stamp = restTemplate.getForObject( ip + key.getId(), PiStamp.class);
			//PiStamp quote = restTemplate.getForObject("http://localhost:8080/pi/247615E", PiStamp.class);
			System.out.println("GOT Answer from server");

			return piStamp;
		}catch (Exception e){
			if(e.getMessage().equals("400 Bad Request"))
				return new PiStamp();
			log.info(e.getMessage());
			log.info("Error starting connection to server");
		}
		return null;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
