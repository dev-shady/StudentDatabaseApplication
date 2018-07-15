# StudentDatabaseApplication
A small springboot application with postgres sql database connectivity.


When uploading in aws, change the 
'spring.datasource.url' to the correct end point.
spring.datasource.url=jdbc:postgresql://<database-endpoint>/<database-name>
  
Also, update the corresponding username and password.
Create fresh jar or war file and deploy.

For generating jar file, change the directory to the project directory and run cmd './mvnw clean install'
The output folder will be - 'StudentDatabaseApplication/target/StudentDatabaseApplication-0.0.1-SNAPSHOT.jar'

=======================================================================================================================

I will update the complete aws deployment procedure soon.

