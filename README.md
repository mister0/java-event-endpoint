# This repository contains a simple spring application that acts as an endpoint for events
### The application receives post requests and communicate via sockets to the python server [here](https://github.com/mister0/python-socket-server) to save it in a file

Before running this application you need to make sure : 

* You have java installed from [here](https://java.com/en/download/help/index_installing.xml)
* You have maven installed from [here](https://maven.apache.org/install.html)
* You have postman installed from [here](https://www.getpostman.com/downloads/)


 To run the application : 
* Make sure you already have followed the README [here](https://github.com/mister0/python-socket-server) to run the python socket server 
* Make sure in the `application.properties` file you are using the same IP and PORT values as the python socket server
* To build the project, in the folder containing `pom.xml` use the terminal to run `mvn package`
* Run the project using `java -jar target/java-app-0.1.0.jar`
* Use postman to import the included file `Test-app.postman_collection.json` and run the post request
* Observe in the 2 terminals for the 2 applications how the event is being parsed and processed, change the 
event parameters in postman and make sure it is being appended to `events.csv` file
