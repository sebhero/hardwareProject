package com.hardware.helper;

import java.io.*;
import java.util.Properties;

/**
 * Created by jonatan on 2016-04-19.
 */
public class ConfigReader {

    private boolean usingPi;
    private String ip;
    private String piPort;
    private String compPort;

    public void loadConfig(){
        Properties prop = new Properties();
        File configFile = new File("./files/configFile");

        try {
            InputStream input = new FileInputStream(configFile);
            prop.load(input);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.ip =  prop.getProperty("Ip");
        this.usingPi = Boolean.parseBoolean(prop.getProperty("Pi"));
        this.piPort = prop.getProperty("PiPort");
        this.compPort = prop.getProperty("CompPort");
    }

    public boolean isUsingPi() {
        return usingPi;
    }

    public String getIp() {
        return ip;
    }

    public String getPiPort() {
        return piPort;
    }

    public String getCompPort() {
        return compPort;
    }
}
