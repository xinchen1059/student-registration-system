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

| Category             | Description                                                                      |
| -------------------- | -------------------------------------------------------------------------------- |
| Build Tool           | Maven: Configuration + Dependency                                                |
| IDE                  | Used both Eclipse and Intellij for code development, debugging and deployment    |
| Authentication       | JWB to generate and verify user tokens used for authenticate the update request  |
| Server Container     | Tomcat servlet container to host the application                                 |
| Database and Access  | Used SQLite3 to create local database and JDBC framework to connect to the DB    |
| API                  | Build Restful API CRUD operations using Spring Framework                         |
| Unit test            | JUnit                                                                            |

5. Create Database Table
   Creating a persistent DB named registration.db using sqlite3, and create the able under this DB:
   > sqlite3 registration.db
   > CREATE TABLE students (
      ...> id INTEGER PRIMARY KEY AUTOINCREMENT,
      ...> first_name char(50),
      ...> last_name char(50),
      ...> email char(100),
      ...> major char(50),
      ...> school_year int
      ...> );
   
   >CREATE TABLE courses (
      ...> id INTEGER PRIMARY KEY AUTOINCREMENT,
      ...> course_name char(50),
      ...> instructor char(50),
      ...> time char(50),
      ...> class_room char(50)
      ...> );
   
   PRAGMA foreign_keys = 1
   CREATE TABLE records (
      ...> id INTEGER PRIMARY KEY AUTOINCREMENT,
      ...> student_id int,
      ...> course_id int,
      ...> score int,
      ...> FOREIGN KEY(student_id) REFERENCES students(id),
      ...> FOREIGN KEY(course_id) REFERENCES courses(id)
      ...> );
   
   
   Insert a test record into the DB to test the DB working.
   INSERT INTO students VALUES(1, 'Xin', 'Chen', 'xinchen@gmail.com', 'CS', 3);
   INSERT INTO courses VALUES(1,'CS50','David Malan','Wed 2pm-4pm','HA001');
   INSERT INTO records VALUES(1,2,2,95);
   
   6. CRUD Operations
   Create (authentication required)
   Student table:
   > curl http://localhost:8080/student-registration-system/rest/student --header "Content-Type: application/json" --header "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTIzNDU2fQ.JebLvZCXxQoDJSObmP4TltqaJMQMWoun9ON30OlXC6o" -X POST  -d '{"major":"CS","email":"mm@gmail.com","firstName":"New","lastName":"Student","schoolYear": "5"}'  
   
   Course table:
   > curl http://localhost:8080/student-registration-system/rest/course --header "Content-Type: application/json" --header "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTIzNDU2fQ.JebLvZCXxQoDJSObmP4TltqaJMQMWoun9ON30OlXC6o" -X POST  -d '{"courseName":"EE101","instructor":"Baris Taskin","time":"Fri 2pm-4pm","ClassRoom":"BS001"}'
   
   record table
   >curl http://localhost:8080/student-registration-system/rest/registration --header "Content-Type: application/json" -X POST  -d '{"studentId":"2","courseId":"1","score":"0"}'
   Read 
   Student table:
   > curl http://localhost:8080/student-registration-system/rest/student/{id}
    For example: 
   curl http://localhost:8080/student-registration-system/rest/student/1
   > curl http://localhost:8080/student-registration-system/rest/student/getall 
   
   Course table:
   > curl http://localhost:8080/student-registration-system/rest/student/{id}
    For example: 
   curl http://localhost:8080/student-registration-system/rest/student/1
   > curl http://localhost:8080/student-registration-system/rest/student/getall 
   
   Course table:
   > curl http://localhost:8080/student-registration-system/rest/record/{id}
    For example: 
   curl http://localhost:8080/student-registration-system/rest/record/1
   > curl http://localhost:8080/student-registration-system/rest/getstudent/getstudents/2
    For example:
   curl http://localhost:8080/student-registration-system/rest/registration/getstudents/2
   Update
   Student table:
   
   > curl http://localhost:8080/student-registration-system/rest/student/2 --header "Content-Type: application/json" -X PUT -d '{"major":"CS","email":"ljm@gmail.com","firstName":"Lianjun","lastName":"Ma","schoolYear":"5"}'  
   
   Course table:
   > curl http://localhost:8080/student-registration-system/rest/course/1  --header "Content-Type: application/json" -X PUT -d '{"courseName":"ECE101","instructor":"Baris Taskin","time":"Fri 2pm-4pm","classRoom":"BS002"}'
   
   Record table:
   >curl http://localhost:8080/student-registration-system/rest/registration --header "Content-Type: application/json" --header "Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTIzNDU2fQ.JebLvZCXxQoDJSObmP4TltqaJMQMWoun9ON30OlXC6o" -X POST  -d '{"studentId":"2","courseId":"1","score":"0"}'
   
   Delete
   Student table:
   > curl -X "DELETE" http://localhost:8080/student-registration-system/rest/student/3
   
   Course table:
   > curl -X "DELETE" http://localhost:8080/student-registration-system/rest/course/2
   
   Record table:
   > curl -X "DELETE" http://localhost:8080/student-registration-system/rest/record/2
