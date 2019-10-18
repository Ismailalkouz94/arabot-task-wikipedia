### arabot-task-wikipedia

-this project created using Spring boot as java REST framework and Mysql DB 

-things must be installed before run project:
 JDK 8,
 Maven,
 mysql database
 
 Note: project have embedded tomcat 
 
-to build project and run it by WAR file: 
  go to project root directory and use this command in terminal : "MVN install"
  then get WAR file in "target" folder and rename it to "wikipedia" then deploy it on tomcat or other app server
  
-to run project from IDE: 
  run class "WikipediaApplication"
  
-to run test cases: 
 run class "WikipediaApplicationTests" in test package 
 please be careful with test order 
 
 -API's
  search : http://localhost:8080/wikipedia/articles/Abdullah
  statistics : http://localhost:8080/wikipedia/articles
  
  note: use your host and port no
  
  additional: use postman collection "arabot-wiki-task.postman_collection.json" located in project dir
  
 -use "application.properties" file to change/add database and other config 
 
 #### Reference Documentation
 For further reference, please consider the following sections:
 
 * [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
 * [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/maven-plugin/)
 * [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.2.0.RELEASE/reference/htmlsingle/#boot-features-jpa-and-spring-data)
 
 #### Guides
 The following guides illustrate how to use some features concretely:
 
 * [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
 * [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
