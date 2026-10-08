package com.homeautomation.model;

/**
 * Represents a fan device in the home automation system.
 * Inherits common properties and control operations from Device.
 */
public class Fan extends Device {

    // Default constructor
    public Fan() {
        super();
    }

    // Creates a fan with its ID, name and current status
    public Fan(int id, String name, boolean status) {
        super(id, name, "Fan", status);
    }

    // Turns the fan ON using the parent class method
    @Override
    public void turnOn() {
        super.turnOn();
    }

    // Turns the fan OFF using the parent class method
    @Override
    public void turnOff() {
        super.turnOff();
    }
}