package com.homeautomation.model;

public class Light extends Device {

    public Light() {
        super();
    }

    public Light(int id, String name, boolean status) {
        super(id, name, "Light", status);
    }

    @Override
    public void turnOn() {
        super.turnOn();
    }

    @Override
    public void turnOff() {
        super.turnOff();
    }
}