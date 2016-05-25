/*package com.hardware;

import com.hardware.model.PiStamp;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = HardwareProjectApplication.class)
//@WebAppConfiguration
public class HardwareProjectApplicationTests {


    @Test(expected = HttpClientErrorException.class)
    public void testingssl() {

        BasicAuthRestTemplate restTemplate = new BasicAuthRestTemplate("piUser", "pass");
        String url = "https://projektessence.se/api/pi/A448182B3";

        BasicAuthRestTemplate.trustSelfSignedSSL();
        HttpEntity<String> request = new HttpEntity<>("");
        //String got = "";
        PiStamp got = new PiStamp();

        //try {
        // Make the HTTP GET request to the Basic Auth protected URL
        //got = restTemplate.exchange(url, HttpMethod.GET, request,  PiStamp.class);
        try {
            ResponseEntity<PiStamp> gotb = restTemplate.getForEntity(url, PiStamp.class);
        }catch(HttpClientErrorException e) {
            //System.out.println(gota.getBody())
            System.out.println(e.getStatusCode());
        }

        //Assert.assertNotNull(got);
        //Assert.assertEquals(HttpStatus.BAD_REQUEST, gotb.getStatusCode());
    }

}*/


