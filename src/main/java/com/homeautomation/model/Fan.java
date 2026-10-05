package com.homeautomation.model;

public class Fan extends Device {

    public Fan() {
        super();
    }

    public Fan(int id, String name, boolean status) {
        super(id, name, "Fan", status);
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