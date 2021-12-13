# by-coders-backend

**Back-End**: The BackEnd was developed in Java 11 using Spring Boot 2 (v2.5.6), developed in Spring 5 (very good support), taking advantage of the optimization of tasks automation resources.
Here I use other technologies such as spring-data, spring-web, lombok, spring-boot-maven-plugin, etc... I didn't have the need for others that could be added too.

For unit tests, JUnit 4.13.2 was used with spring-test and MockMvc to mock the data.

To run the backend it has to be run like this:

$ mvn spring-boot:run spring-boot.run.jvmArguments=-noverify -XX:TieredStopAtLevel=1 spring-boot.run.mainClass=com.example.BasicApplication Env.SPRING_OUTPUT_ANSI_ENABLED=always

I love you too:

$  mvn clean package spring-boot:repackage
$  java -jar target/basic-0.0.1-SNAPSHOT

more arguments can be added (memory, cache, etc...) also to improve performance, depending on the infrastructural context where it is executed.

**ENDPOINTS**

When executing these lines, it will listen on port 8080, which the client (frontend) will get its endpoints, describing the endpoints (I could have used swagger to document the services but as there are few, I thought it was not worth it):

http://localhost:8080/processFile

* --> PARAMETERS: A MultipartFile named 'file'
* --> RETURN: A boolean success/error
* --> FUNCTION: Adds the operations of the files in the DB

http://localhost:8080/listMovimentos

* --> PARAMETERS: ---
* --> RETURN: A list of objects
* --> FUNCTION: Lists operations grouped by store name

http://localhost:8080/reset

* --> PARAMETERS: ---
* --> RETURNS: ---
* --> FUNCTION: Clear operation table

Postman was used to test the endpoints, here is an example of the /listMovimentos return:
```
{
    "3 BROTHERS GROCERY": 28092.0,
    "LOJA DO Ó - BRANCH": 609.28,
    "AVENUE MARKET": 9340.8,
    "BAR DO JOÃO": 1624.0,
    "LOJA DO Ó - MATRIZ": 1736.0
}
```

**Database**: MySQL Server 8.0.27-1debian10 was used (image hosted in Hub Docker), for record persistence, to download the image from hub-docker site (https://hub. docker.com/_/mysql).

* Settings are described in application.properties (src/main/resources/application.properties).
* I add for more details on the creation of the DB in another file of the project my-coders-backend, where all the trace of the command-line-client of the platform is described.
