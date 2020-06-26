# Bowling Game 

### Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Running the tests](#running-the-tests)
* [Deployment](#deployment)

### General info
This demo project implements the traditional scoring model of bowling game.<br />
The purpose of this small application is to provide a quick **Java/Java EE**, **JSF/Primefaces**, *unit tests* and *css in Primefaces* skills overview.

### Technologies
Project is created with:<br />
* **JavaEE-Api** version: 8.0.1<br />
* **Primefaces** version: 8.0<br />
* **JUnit** version: 5.6.1<br />
* **Weld-junit5** version: 2.0.1.Final *(for `@Named` bean unit test)*<br />
* **Hamcrest** version: 2.2 *(sample of matchers usage in GameHandlerTest class)*<br />
* **Maven** version: 3.6.0

### Running the tests
Maven will launch test automaticly during build using **junit-platform-runner** and **maven-surefire-plugin**.

### Deployment
Application was tested on **Wildfly 19.0.0.Final** and **Payara 5.201**.<br /><br />
If you do not want to install an application server, you can use Payara-micro-maven-plugin with command:
```
mvn clean package payara-micro:start / stop
```
This command will download lightweight Payara Micro to your Maven repository and launch the application on top of Payara.<br />
After this you can access application at: http://localhost:8095/Bowling/ <br /><br />