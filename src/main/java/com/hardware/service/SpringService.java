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

import java.net.UnknownHostException;
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

	/**
	 * Still method will send the rfid to the server,
	 * then it will return the answer as an piStamp
	 * @param key rfid
	 * @return pistamp
     */
	public PiStamp sendRfid(RfidKey key) {
			BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("piUser", "pass");
			String url = "https://projektessence.se/api/pi/" + key.getId();

			BasicAuthRestTemplate.trustSelfSignedSSL();

			PiStamp piStamp = new PiStamp();

        /*log.info("Sending data to server " + key.getId());
		///do A SERVER CALL
		String plainCreds = "piUser:pass";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);

		HttpEntity<String> request = new HttpEntity<>(headers);*/
		//// TODO: 2016-04-12 Change Server IP
		try{
			piStamp = restTemplate.getForObject(url, PiStamp.class);
			//ResponseEntity<PiStamp> response = restTemplate.exchange("https://projektessence.se/api/pi/" + key.getId(), HttpMethod.GET, request, PiStamp.class);
			//PiStamp piStamp = response.getBody();
			//PiStamp stamp = restTemplate.getForObject( ip + key.getId(), PiStamp.class);
			//PiStamp quote = restTemplate.getForObject("http://localhost:8080/pi/247615E", PiStamp.class);
			System.out.println("GOT Answer from server");
			System.out.println(piStamp.toString());
			return piStamp;
		}catch (Exception e){
			piStamp.setPiStatus(e.getMessage());
			log.info(e.getMessage());
			log.info("Error starting connection to server");
		}
		return piStamp;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getPiTime(){
		BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("piUser", "pass");
		String urlTime = "https://projektessence.se/api/pi/time";
		BasicAuthRestTemplate.trustSelfSignedSSL();
		PiStamp piTime = new PiStamp();
		try{
			piTime = restTemplate.getForObject(urlTime, PiStamp.class);
			System.out.println(piTime.getDate());
		}catch(Exception e){
			log.info(e.getMessage());
			System.out.println(piTime.getDate());
		}
		return piTime.getDate();
	}
}
