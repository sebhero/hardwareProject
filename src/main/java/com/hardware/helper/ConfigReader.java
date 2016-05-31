package com.hardware.helper;

import java.io.*;
import java.util.Properties;

/**
 * @author Jonatan Fridsten
 *         <p>
 *         This class makes is possible to change the ip,port and if we are using pi,
 *         even when its an jar.
 */
public class ConfigReader {

    private boolean usingPi;
    private String ip;
    private String piPort;
    private String compPort;

    /**
     * Reads the file then saves it as field variables
     */
    public void loadConfig() {
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

        this.ip = prop.getProperty("Ip");
        this.usingPi = Boolean.parseBoolean(prop.getProperty("Pi"));
        this.piPort = prop.getProperty("PiPort");
        if (piPort == null) {
            this.piPort = "/dev/ttyACM0";
        }
        this.compPort = prop.getProperty("CompPort");
    }

    /**
     * Returns if the program is using a pi or not
     *
     * @return false not on pi, true on a pi
     */
    public boolean isUsingPi() {
        return usingPi;
    }

    /**
     * Returns the current ip
     *
     * @return ip as an string
     */
    public String getIp() {
        return ip;
    }

    /**
     * Returns what port that is connected on the pi
     *
     * @return port value
     */
    public String getPiPort() {
        return piPort;
    }

    /**
     * Returns the current USB-port that is used
     *
     * @return port value
     */
    public String getCompPort() {
        return compPort;
    }
}
