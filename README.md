# Book Collection Application

by Maria Veghova

#
The purpose of this application is to enable the user to manage books and authors using CRUD operations.

###Prerequisites:
 - JDK 1.8+
 - Maven 3+
 - MariaDB 10.2.8+

###Build and start:
    mvn clean install
This command also creates an empty database called 'bc' if MariaDB service is running.
    

    mvn flyway:migrate
This command creates the database structure.

Then start the application as a standard Spring Boot app. 
It will be running on http://localhost:8081

###API Documentation:
The auto-generated Swagger API Documentation is available here:
http://localhost:8081/swagger-ui.html


###Hint:
If you are using IntelliJ Idea, it comes with an HTTP client. 
There is a file prepared with all request on path: 
    
    src/main/resources/rest.http
