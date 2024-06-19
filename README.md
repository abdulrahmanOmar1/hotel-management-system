# Inventory Management System
## Overview :

The Hotel Management System is designed to streamline operations for both hotel employees and guests. This system offers a range of functionalities that enhance the customer experience and improve operational efficiency. It is developed using Spring Boot and provides a set of RESTful APIs to support web and mobile interfaces. The system ensures secure, role-based access control, allowing different functionalities for admin and customer roles. The project includes detailed API documentation, secure JWT-based authentication, and the use of Docker for easy deployment.
 ## ERD (Entity Relationship Diagram):

![Screenshot 2024-04-06 141546](https://github.com/abdulrahmanOmar1/hotel-management-system/blob/master/ERD.png)


<h2>Bill Table</h2>
<p><strong>Attributes:</strong> id, reservation, date, dueDate, amount, status.</p>
<strong>Relationships:</strong>
<ul>
    <li>Many-to-One relationship with the Reservation table (each bill is associated with one reservation).</li>
</ul>

<h2>HouseKeepingTask Table</h2>
<p><strong>Attributes:</strong> id, room, employee, taskDescription, taskDate, completedDate, status.</p>
<strong>Relationships:</strong>
<ul>
    <li>Many-to-One relationship with the Room table (each housekeeping task is associated with one room).</li>
    <li>Many-to-One relationship with the User table (each housekeeping task is performed by one employee).</li>
</ul>

<h2>Reservation Table</h2>
<p><strong>Attributes:</strong> id, customer, room, checkInDate, checkOutDate, numberOfDays, price.</p>
<strong>Relationships:</strong>
<ul>
    <li>Many-to-One relationship with the User table (each reservation is made by one customer).</li>
    <li>Many-to-One relationship with the Room table (each reservation is associated with one room).</li>
</ul>
<h2>Room Table</h2>
<p><strong>Attributes:</strong> id, roomNo, type, price, capacity, size, status.</p>
<strong>Relationships:</strong>
<ul>
    <li>Referenced by the HouseKeepingTask table.</li>
    <li>Referenced by the Reservation table.</li>
</ul>
<h2>User Table</h2>
<p><strong>Attributes:</strong> id, name, email, phoneNumber, pass, role.</p>
<strong>Relationships:</strong>
<ul>
    <li>Referenced by the HouseKeepingTask table (employees).</li>
    <li>Referenced by the Reservation table (customers).</li>
</ul>
<h2>Summary</h2>
<p>The User entity represents all users in the system, categorized by roles (EMPLOYEE, ADMIN, CUSTOMER).</p>
<p>Reservation links customers (User) to rooms (Room).</p>
<p>Bill is associated with a specific reservation.</p>
<p>HouseKeepingTask is linked to rooms and the employees (User) who perform the tasks.</p>
<p>These relationships ensure a structured and organized database schema, facilitating effective management of reservations, billing, and housekeeping tasks within the hotel management system.</p>

# This is our database that we will use while building the project :
https://github.com/abdulrahmanOmar1/hotel-management-system/blob/master/Dump20240619.sql


# <img src="https://raw.githubusercontent.com/swagger-api/swagger.io/wordpress/images/assets/SW-logo-clr.png" height="60">  
### Meaning of swgger :
Swagger UI allows anyone to visualize and interact with the API’s resources without having any of the implementation logic in place. It’s automatically generated from your OpenAPI (formerly known as Swagger) Specification, with the visual documentation making it easy for back end implementation and client side consumption.
## My APIs are built using the OpenAPI 3.1.0 specification.
###  My link to Swagger after run Program :
http://localhost:9090/swagger-ui/index.html#/
  

## Testing with Postman <img src="https://www.svgrepo.com/show/354202/postman-icon.svg" height="60">
A Postman collection containing tests for all endpoints of this project has been included. You can import this collection into Postman for testing purposes.

https://github.com/abdulrahmanOmar1/hotel-management-system/blob/master/swagger.json
## Docker🐋
<p><strong>DockerHub Repository:</strong> <a href="https://hub.docker.com/repositories/abdulrahamnomar1">https://hub.docker.com/repositories/abdulrahamnomar1</a></p>

  ## Pull the Docker Image
 Open a terminal or command prompt and execute the following command to pull the Docker image from DockerHub:
    <pre><code>docker pull abdulrahamnomar1/project2hotel-management-system</code></pre>

  ## Run the Docker Container
 Once the image is pulled, run a Docker container using the following command:
    <pre><code>docker run -p 9090:9090 abdulrahamnomar1/project2hotel-management-system</code></pre>

  ## Access the Application
Once the container is running, you can access the Hotel Management System through your web browser or any HTTP client at:
    <p><a href="http://localhost:9090">http://localhost:8080</a></p>
<hr>

# Hotel Management System Features

## 1. Customer Management
- User registration and login
- Manage user profiles (view, update, change passwords)

## 2. Employee Management
- Admin can manage hotel employees and staff

## 3. Search Functionality
- Search for reservations by customer name or ID and date
- Search customer info and available rooms with details, price, facilities, capacity, size, and features

## 4. Reservation Management
- Book, modify, and cancel reservations
- Reservation cancellation request needs admin approval

## 5. Room Management
- Manage room types, availability, and status by admin users

## 6. Check-In/Check-Out Process
- Manage customer arrival and departure processes by admin users

## 7. Housekeeping Management
- Schedule and track housekeeping tasks and employees

## 8. Billing Functionality
- Generate and manage invoices for customer reservations

## 9. Role-Based Access Control
- Different functionalities available based on user roles (admin, customer)

## 10. Security with JWT (JSON Web Token)
- Secure APIs using JWT
- Publicly accessible APIs and authenticated/authorized user APIs
- Signup and authenticate APIs
- Only two types of user roles: customer and admin

## Additional Features
- Create Docker image and push to DockerHub
- Push project on GitHub with proper Git commands
- ER diagram with proper notations
- Code documentation with OAS 3.1.0
- Entity relations in the DAO layer
- API versioning in at least 3 APIs
- Postman collection for testing scenarios
- Database initialization with sample data
- Data validation and proper exception handling
  
<hr>

# This project was completed by:

### Abdulrahman Omar
- Student ID: 1201811
- Email: aboodomaral@gmail.com

### Yousef Injas
- Student ID: 1200643
- Email: injasyousef5@gmail.com

We hope you find the features we developed useful and that the project meets your expectations.

# What we learned from this project 
**Application Design and Architecture:**
- Design complex applications like Hotel Management Systems, defining entities, relationships, and business logic.
- Implement RESTful APIs using Spring Boot for frontend-backend communication.

**Database Management:**
- Manage entity relationships and database operations in the DAO layer.
- Ensure data integrity through transactions and migrations.

**Security Practices:**
- Implement JWT-based authentication and role-based access control.
- Secure sensitive data and prevent common vulnerabilities.

**Containerization and Deployment:**
- Use Docker for containerization, deployment, and scalability.
- Manage multi-container applications with DockerHub and docker-compose.
ibility.

**Collaborative Development:**
- Use Git and GitHub for collaborative development, including branching and pull requests.
- Follow best practices for version control.

**Testing and Validation:**
- Implement data validation and exception handling.
- Use Postman to simulate user journeys and validate API functionality.

**Project Management and Documentation:**
- Organize tasks, milestones, and deadlines.
- Write clear documentation (e.g., README.md) for project overview and setup instructions.
