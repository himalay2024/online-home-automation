package com.homeautomation.model;

import com.homeautomation.interfaces.Controllable;

/**
 * Represents a home automation device.
 * Stores basic device information and provides
 * operations to control its ON/OFF status.
 */
public class Device implements Controllable {

    private int id;
    private String name;
    private String type;
    private boolean status;

    // Default constructor
    public Device() {
    }

    // Constructor used to create a device with its details
    public Device(int id, String name, String type, boolean status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    // Returns the unique ID of the device
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Returns the device name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Returns the type of the device
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Returns true if the device is currently ON
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Turns the device ON
    public void turnOn() {
        status = true;
    }

    // Turns the device OFF
    public void turnOff() {
        status = false;
    }
}