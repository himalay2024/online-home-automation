# Online Home Automation Monitoring System

A Java-based web application for monitoring and controlling home devices through a simple web interface.

## 📌 Project Overview

The **Online Home Automation Monitoring System** allows users to manage home devices such as lights, fans, and air conditioners from a web browser.

The system also stores sensor readings and device activity history in a MySQL database.

## ✨ Features

- User login and logout
- Device management
- Add home devices
- Turn devices ON/OFF
- Temperature and humidity monitoring
- Sensor reading storage
- Device activity history
- Responsive web interface
- MySQL database integration
- JDBC-based database connectivity
- Servlet-based web application

## 🛠️ Technologies Used

- **Java**
- **Jakarta Servlets**
- **JDBC**
- **MySQL**
- **HTML5**
- **CSS3**
- **JavaScript**
- **Apache Tomcat 10**
- **Maven**
- **Git & GitHub**

## 🏗️ System Architecture

```text
Browser
   ↓
Servlet
   ↓
Service Layer
   ↓
DAO Layer
   ↓
JDBC
   ↓
MySQL Database
```

## 📂 Project Structure

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
│           ├── devices.html
│           └── sensor.html
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

## 🗄️ Database

The project uses a MySQL database named:

```text
home_automation
```

### Main Tables

| Table | Purpose |
|---|---|
| `users` | Stores user login information |
| `devices` | Stores home device information |
| `sensor_readings` | Stores temperature and humidity readings |
| `device_history` | Stores ON/OFF device activity |

The complete database setup script is available in:

```text
database/schema.sql
```

## ☕ Java Concepts Implemented

This project demonstrates several Core Java concepts:

- Object-Oriented Programming
- Encapsulation
- Inheritance
- Polymorphism
- Interfaces
- Exception Handling
- Collections and Generics
- Multithreading
- Synchronization
- JDBC
- DAO design pattern

## 🌐 Web Components

The application uses Jakarta Servlets for server-side processing.

Important servlets include:

- `LoginServlet`
- `LogoutServlet`
- `DeviceServlet`
- `DeviceListServlet`
- `DeviceControlServlet`
- `SensorServlet`
- `HistoryServlet`

## 🔐 Login

For demonstration purposes, the project includes a demo user:

```text
Username: admin
Password: admin123
```

> This is a college demonstration project. Passwords are currently stored in the database in simple form and should be hashed in a production application.

## ▶️ How to Run

### 1. Requirements

Install:

- Java JDK
- Maven
- MySQL
- Apache Tomcat 10

### 2. Create Database

Open MySQL and run:

```sql
SOURCE database/schema.sql;
```

Or execute the SQL commands from:

```text
database/schema.sql
```

### 3. Configure Database Connection

Open:

```text
src/main/java/com/homeautomation/util/DBConnection.java
```

Set your local MySQL password in the database connection configuration.

### 4. Build the Project

From the project root directory:

```bash
mvn clean package
```

The WAR file will be generated in:

```text
target/online-home-automation.war
```

### 5. Deploy on Tomcat

Copy the generated WAR file to the Tomcat `webapps` directory.

Then start Apache Tomcat.

### 6. Open the Application

Open:

```text
http://localhost:8080/online-home-automation/login.html
```

## 📊 Project Flow

```text
User Login
    ↓
Dashboard
    ↓
View Devices
    ↓
Turn Device ON/OFF
    ↓
Store Device History
    ↓
Monitor Sensor Readings
    ↓
Store Temperature & Humidity
    ↓
View Device History
```

## 🎯 Project Objective

The main objective of this project is to demonstrate how Java web technologies, JDBC, MySQL, object-oriented programming, and servlet-based architecture can be combined to create a simple home automation monitoring system.

## 👨‍💻 Project Status

**Current Status:** Functional prototype

Implemented and tested:

- Login
- Logout
- Device saving
- Device listing
- Device ON/OFF control
- Sensor reading storage
- Device history
- MySQL database integration
- JDBC connectivity
- Responsive UI

## 📚 Academic Purpose

This project is developed for academic learning and demonstrates practical implementation of Java, Web Technologies, Database Management, JDBC, Servlets, and Object-Oriented Programming.