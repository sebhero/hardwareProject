# hardwareProject

The smart punch-clock [hardware part]

## Getting Started

This is a punch-clock system. built on a Spring boot server (this part) with an Angularjs webinterface,
for administrators to manage the users and timestamps.
Using Rfid-card to punch in/out.

* There is a Android app ([android app](https://github.com/GurraB/Projekt_1)) for user to check there timestamps (punch in/out).
* Thers is a JavaFx-admin-app ([admin app](https://github.com/Coweete/AdminAppWithFxml)) for registering new Rfid-cards.
* There is a Server with Spring boot ([server](https://github.com/AntonHellbe/ServerProject/)).

## To run the project

build the project using maven:
* mvn clean package

Then you can run the jar file on the raspberry pi:
* java -jar xxxx.jar *xxxx =jar filename

### Prerequisities

you need to have:
- maven

## Deployment

# To deploy the system:
build the project using maven:
* mvn clean package

then copy the created jar file to you raspberry pi 2 *or later.
* open port 44344
* run the jar file, done!

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

