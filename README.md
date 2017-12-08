# HAREUI
HARE Service is a service that provides HARE

## Getting Started
1. Create a "dataset" folder once the respository is cloned.
2. Add the turtle files into the folder. (Only after being processed by the HAREWriter).  
3. Rename the files to match those of the name of the dataset.

### Prerequisites

Maven is the only pre-requisite for  . The instructions to install maven can be found [here](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).

### Installing

1. Once the maven is up and running. Clone the repository using the following command in a desired folder.
2. Navigate to the base folder of the project and run the following command
    
    `mvn clean install` 

3. Start  the server as as follows 

    `mvn spring-boot:run`

4. The UI should be available at 

    `localhost:8080/index`
