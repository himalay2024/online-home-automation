package com.homeautomation.model;

/**
 * Represents a light device in the home automation system.
 * Inherits common device properties and control methods from Device.
 */
public class Light extends Device {

    // Default constructor
    public Light() {
        super();
    }

    // Creates a light with its ID, name and current status
    public Light(int id, String name, boolean status) {
        super(id, name, "Light", status);
    }

    // Turns the light ON using the parent class method
    @Override
    public void turnOn() {
        super.turnOn();
    }

    // Turns the light OFF using the parent class method
    @Override
    public void turnOff() {
        super.turnOff();
    }
}