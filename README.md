# student-registration-system

1. Objective
The objective of the project is to build a Java web application that can perform basic functionalities of a student registration system, in order for me to understand and learn further on:
   * How to build, develop and debug web-application using Eclipse as IDE and Maven as build tool;
   * Basic client server architecture and backend components such as server and databases.
   * Java programming and OOD.

The objective is NOT to build a full fledged student registration system that have complex business logic, although that can be extended in the future.

2. Functionalities
The basic functionality is as follows:
   * Student can register themselves through API call by providing name, email, major and school year.
   * Everyone can read the list of registered students by making API calls, but do not have edit, deletion permission.
   * Only the system administrator have the ability to edit and remove registered students.

3. System Overview
![Alt text](./system_overview.png)

4. Tools, Technology and Frameworks
| Category           | Description                                                                    |
|--------------------|--------------------------------------------------------------------------------|
| Build Tool         | Maven: Configuration + Dependency                                              |
| IDE                | Used both Eclipse and Intellij for code development, debugging and deployment  |
| Authentication     | JWB to generate and verify user tokens used for authenticate the update request|
| Server Container   | Tomcat servlet container to host the application                               |
| Database and Access| Used SQLite3 to create local database and JDBC framework to connect to the DB  |
| API                | Build Restful API CRUD operations using Spring Framework                       |
| Unit test          | JUnit                                                                          |

5. Database Setup

Create Database Table
Creating a persistent DB named registration.db using sqlite3, and create the students table under this DB:
> sqlite3 registration.db
> CREATE TABLE students (
     id int,
     first_name char(50),
     last_name char(50),
     email char(100),
     major char(50),
     school_year int
);

6. CRUD Operations
Create 
> curl http://localhost:8080/student-registration-system/rest/studentRegistrationSystem/student
  -header "Content-Type: application/json" -X PUT -data '{"major":"CS","email":"jclu@gmail.com","firstName":"JC","lastName":"Lu","schoolYear":"4"}'

Read
> curl http://localhost:8080/student-registration-system/rest/studentRegistrationSystem/student/{id}
For example: curl http://localhost:8080/student-registration-system/rest/studentRegistrationSystem/student/1
> curl http://localhost:8080/student-registration-system/rest/studentRegistrationSystem/getall

Update
> curl http://localhost:8080/student-registration-system/rest/studentRegistrationSystem/student --header "Content-Type: application/json" --header "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTIzNDU2fQ.JebLvZCXxQoDJSObmP4TltqaJMQMWoun9ON30OlXC6o" -X POST  -d '{"major":"CS","email":"mm@gmail.com","firstName":"Maomao","lastName":"Lyu","schoolYear": "5"}'

Delete
> curl -X "DELETE" http://localhost:8080/student-registration-system/rest/studentRegistrationSystem/student/1533

