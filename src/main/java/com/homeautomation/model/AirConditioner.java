package com.homeautomation.model;

public class AirConditioner extends Device {

    public AirConditioner() {
        super();
    }

    public AirConditioner(int id, String name, boolean status) {
        super(id, name, "Air Conditioner", status);
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