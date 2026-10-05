-- Online Home Automation Monitoring System
-- Database Schema

CREATE DATABASE IF NOT EXISTS home_automation;

USE home_automation;


-- Users table
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);


-- Devices table
CREATE TABLE IF NOT EXISTS devices (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    status BOOLEAN DEFAULT FALSE,

    FOREIGN KEY (user_id)
        REFERENCES users(id)
);


-- Sensor readings table
CREATE TABLE IF NOT EXISTS sensor_readings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    device_id INT NOT NULL,
    temperature DECIMAL(5,2),
    humidity DECIMAL(5,2),
    recorded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (device_id)
        REFERENCES devices(id)
);


-- Device history table
CREATE TABLE IF NOT EXISTS device_history (
    id INT PRIMARY KEY AUTO_INCREMENT,
    device_id INT NOT NULL,
    action VARCHAR(20) NOT NULL,
    action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (device_id)
        REFERENCES devices(id)
);


-- Demo login user
INSERT IGNORE INTO users (username, password)
VALUES ('admin', 'admin123');