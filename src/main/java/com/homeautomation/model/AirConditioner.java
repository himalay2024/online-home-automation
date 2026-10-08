package com.homeautomation.model;

/**
 * Represents an air conditioner in the home automation system.
 * Inherits common device properties and control operations from Device.
 */
public class AirConditioner extends Device {

    // Default constructor
    public AirConditioner() {
        super();
    }

    // Creates an air conditioner with its ID, name and current status
    public AirConditioner(int id, String name, boolean status) {
        super(id, name, "Air Conditioner", status);
    }

    // Turns the air conditioner ON using the parent class method
    @Override
    public void turnOn() {
        super.turnOn();
    }

    // Turns the air conditioner OFF using the parent class method
    @Override
    public void turnOff() {
        super.turnOff();
    }
}