# SolnetTaskManagement
Solnet Task Management API

Instructions to run the Application:-

This code is developed using Maven, Derby Embedded Database and Spring boot framework. All the APIs are well documented using the latest Swagger specifications.
The codebase for this Project is also updated in the github and can be cloned from the URL : https://github.com/sreedhar85in/SolnetTaskManagement.git
Below are the details to run the application,

1. Unzip the zip file "SolnetTaskManagement.zip" or clone the code from the above github link and then use an IDE like Eclipse and import this project as Maven->  Existing Maven Projects.
2. In the next window of import, in the field of "root directory" browse the code base where Pom.xml is present, and click finish.
3. After succesfully importing the project to the Eclipse IDE as above, right click on the project and go to option 'Maven', -> 'Update Project'. Click the checkbox for 'Force update of Snapshots/Releases' and then click 'OK'. This will download all the required dependencies for the project as given in the pox.xml file.
4. After the above step and succesful update of Maven dependencies, again right click on the project, -> "Run As" -> "Maven Install". This will show the build is success.
5. Then navigate to the package "nz.co.solnet" in eclipse and open the file "TaskApplication" which is the main file for the application. Right click on this file and do -> "Run As" -> "Java Application".
6. After 5 seconds, the eclipse will have a console message stating "Tomcat started on port(s): 8080 (http) with context path". This means the application is started and its ready to test.
7. Now since Swagger is implemented for this project to execute the APIs with ease, we can navigate to the below swagger base path of the application,
	http://localhost:8080/swagger-ui.html
	
8. After the swagger URL loads up, please execute the APIs listed under Get,Post,Put,and Delete with the sample data. Swagger already provides sample request and response messages for each of the API's. By default application start-up will insert four records for testing purposes(this sample data is inserted using the sql script, src\main\resources\data.sql).	
	
9.Alternatively the application can also be tested using a postman without Swagger, but with plain API endpoints as below,
		
		a) Fetch data for all the tasks
			- GET "http://localhost:8080/task"
		b) Add a new task
			- POST "http://localhost:8080/task"
			Sample request body for this POST API is as below,
					{
						"id": 5,
						"title": "Supermarket",
						"description": "Icecream",
						"due_date": "2020-04-14",
						"status": "P4",
						"creation_date": "2020-04-13"
					}
		c)Modify a task
			-PUT "http://localhost:8080/task"
			Sample request body for this PUT API is as below,
					{
						"id": 4,
						"title": "SupermarketModified",
						"description": "Icecream",
						"due_date": "2020-04-14",
						"status": "P4",
						"creation_date": "2020-04-13"
					}
		  
		d)Delete a task
			-DELETE "http://localhost:8080/task?ID=4"
			we can change the ID parameter above as per the requirement to delete a task.
			
			
		 f)Fetch data for a single task
		 
			 GET "http://localhost:8080/taskForOne?ID=4"
			 we can change the ID parameter above as per the requirement to fetch a task.
