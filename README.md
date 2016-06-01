# hardwareProject

The smart punch-clock [hardware part]

## Getting Started

This is a punch-clock system. with a Spring Boot server backend.
Using Rfid-card to punch in/out. this part is the hardware part, using Arduino to read Rfid-card
and then sending the data to the server.

* There is a Android app ([android app](https://github.com/GurraB/Projekt_1)) for user to check there timestamps (punch in/out).
* Thers is a JavaFx-admin-app ([admin app](https://github.com/Coweete/AdminAppWithFxml)) for registering new Rfid-cards.
* There is a Server with Spring boot ([server](https://github.com/AntonHellbe/ServerProject/)).

## To run the project

build the project using maven:
* mvn clean package

then you need to configure the Arduino.
* compile the Arduino code file to the Arudino.
* setup the Arduino with Rfid-cardreader
* connect the Arduino to the Raspberry Pi using Usb-cable.

Then you can run the jar file on the raspberry pi:
* java -jar xxxx.jar *xxxx =jar filename

### Prerequisities

you need to have:
- maven
- Arduino UNO
- RFID-card reader
- Raspberry Pi 2 *or later

## Deployment

# To deploy the system:
build the project using maven:
* mvn clean package

then copy the created jar file to you raspberry pi 2 *or later.
* run the jar file
 

## Built With

* Maven - Maybe
* Spring Boot
* Arduino studio
* JavaFX-embedded


## Authors

* **Sebastian Boreback**
* **Johnatan Sona**  
* **Jonatan Fridsten**  


## License

This project is licensed under the MIT License 

