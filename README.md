# Online Home Automation Monitoring System

This is a Java-based web application made as a college project to manage and monitor basic home devices using a web browser.

The main idea of the project is to provide a simple interface where a user can log in, add devices, control them, store sensor readings and check the previous activity of the devices.

## About the Project

In a normal home automation setup, different devices and sensors need to be monitored from one place. This project is a small implementation of that idea using Java Servlets, JDBC and MySQL.

The application currently supports devices such as:

- Light
- Fan
- Air Conditioner

A user can create an account, log in, add devices and turn them ON or OFF. The application also stores temperature, humidity and device activity data in the database.

## Main Features

- User registration
- User login and logout
- Add new home devices
- View devices belonging to the logged-in user
- Turn devices ON and OFF
- Store device ON/OFF history
- Store temperature and humidity readings
- View device history
- Session-based user access
- Responsive web pages
- MySQL database connectivity using JDBC

## Technologies Used

- Java
- Jakarta Servlets
- JDBC
- MySQL
- HTML
- CSS
- JavaScript
- Apache Tomcat 10
- Maven
- Git and GitHub

## How the Application Works

The basic flow of the application is:

```text
Browser
   |
   v
Servlet
   |
   v
Service / Business Logic
   |
   v
DAO
   |
   v
JDBC
   |
   v
MySQL
```

Servlets handle requests coming from the browser. DAO classes are responsible for database operations, while JDBC is used to connect the Java application with MySQL.

## Project Structure

```text
online-home-automation/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/homeautomation/
│       │       ├── model/
│       │       ├── dao/
│       │       ├── service/
│       │       ├── servlet/
│       │       ├── interfaces/
│       │       ├── exception/
│       │       └── util/
│       │
│       └── webapp/
│           ├── WEB-INF/
│           ├── css/
│           ├── js/
│           ├── login.html
│           ├── register.html
│           ├── dashboard.html
│           ├── devices.html
│           ├── sensor.html
│           └── history.html
│
├── database/
│   └── schema.sql
│
├── docs/
│   └── diagrams/
│
├── screenshots/
│
├── pom.xml
├── README.md
└── .gitignore
```

## Database

The application uses a MySQL database named:

```text
home_automation
```

There are four main tables:

| Table | Purpose |
|---|---|
| `users` | Stores registered user information |
| `devices` | Stores devices added by users |
| `sensor_readings` | Stores temperature and humidity readings |
| `device_history` | Stores device ON/OFF activity |

The database creation script is available here:

```text
database/schema.sql
```

The tables are connected using foreign keys. For example, every device is linked to a user and sensor readings are linked to a particular device.

## Java Concepts Used

The project was also developed to cover important Core Java concepts required in the project work.

### OOP

- Classes and objects
- Encapsulation
- Inheritance
- Polymorphism
- Interfaces

For example, `Light`, `Fan` and `AirConditioner` are different types of devices that inherit from the `Device` class.

The `Controllable` interface is used for device operations such as turning a device ON or OFF.

### Exception Handling

A custom `DeviceException` class is used for handling device-related errors.

### Collections and Generics

`ArrayList` and `List<Device>` are used for maintaining collections of devices.

### Multithreading

A `DeviceMonitor` class extends `Thread` and demonstrates basic multithreading. Synchronization is also used while performing the monitoring operation.

## Database and DAO Layer

Database operations are separated from the servlet code using DAO classes.

Some of the important classes are:

```text
DeviceDAO
UserDAO
DBConnection
```

`DBConnection` manages the JDBC connection with MySQL.

`DeviceDAO` handles operations such as:

- Saving devices
- Getting devices
- Updating device status
- Saving device history
- Saving sensor readings
- Getting device history

`UserDAO` handles login and registration.

Prepared statements are used for database queries.

## Web Components

The application uses Jakarta Servlets for handling requests.

Some important servlets are:

```text
LoginServlet
RegisterServlet
LogoutServlet
DeviceServlet
DeviceListServlet
DeviceControlServlet
SensorServlet
HistoryServlet
```

Sessions are used so that users can access only their own devices after logging in.

## Registration and Login

New users can create an account from the registration page.

For demonstration purposes, the database also contains a default user:

```text
Username: admin
Password: admin123
```

This is only for the college project/demo.

Passwords are currently stored in simple form in the database. In a production application, password hashing and stronger authentication should be implemented.

## Running the Project

### Requirements

The following software is required:

- Java JDK
- Maven
- MySQL
- Apache Tomcat 10

### 1. Clone the project

```bash
git clone https://github.com/himalay2024/online-home-automation.git
cd online-home-automation
```

### 2. Set up the database

Open MySQL and execute the SQL file:

```text
database/schema.sql
```

This creates the `home_automation` database and its required tables.

### 3. Configure MySQL

Open:

```text
src/main/java/com/homeautomation/util/DBConnection.java
```

Set the MySQL username and password according to your local MySQL setup.

Do not commit your actual database password to GitHub.

### 4. Build the project

From the project folder, run:

```bash
mvn clean package
```

After a successful build, the WAR file will be created at:

```text
target/online-home-automation.war
```

### 5. Deploy on Tomcat

Copy the generated WAR file into the `webapps` folder of Apache Tomcat 10.

Start Tomcat and wait for the server to start.

### 6. Open the application

Open the following address in a browser:

```text
http://localhost:8080/online-home-automation/login.html
```

## Application Flow

```text
Create Account / Login
          |
          v
       Dashboard
          |
          v
      Add Device
          |
          v
    View Device List
          |
          v
      ON / OFF
          |
          +------> Device History
          |
          +------> Sensor Monitoring
                         |
                         v
                Temperature / Humidity
```

## Screenshots

Some screenshots of the working application are available in the `screenshots` folder.

The screenshots include:

- Login page
- Dashboard
- Device control
- Device history
- Sensor monitoring
- Database device records

## Diagrams

Project diagrams are available inside:

```text
docs/diagrams/
```

These include the system architecture diagram and database ER diagram.

## Current Status

The current version is a functional college project prototype.

The following parts have been implemented and tested:

- User registration
- Login and logout
- Session handling
- Device addition
- Device listing
- Device ON/OFF control
- Device history
- Sensor reading storage
- MySQL integration
- JDBC connectivity
- DAO classes
- Core Java OOP concepts
- Responsive web interface

## Limitations

This project is mainly developed for academic demonstration.

Currently:

- It does not control real physical home appliances.
- Sensor values are entered through the application rather than coming from real sensors.
- Passwords are not hashed yet.
- The application is designed to run locally using Tomcat and MySQL.

## Future Improvements

Some possible improvements for a future version are:

- Connect real IoT sensors and devices
- Add password hashing
- Add user roles and better access control
- Add live sensor updates
- Add notifications for device events
- Connect the application with a cloud-based service
- Add charts for temperature and humidity data

## GitHub Repository

The complete source code is available on GitHub:

```text
https://github.com/himalay2024/online-home-automation
```

## Academic Purpose

This project was developed to understand the practical use of Java, Object-Oriented Programming, JDBC, MySQL, Servlets and web application development.

It also helped in understanding how different parts of a software project such as the user interface, business logic and database can be connected together in one application.