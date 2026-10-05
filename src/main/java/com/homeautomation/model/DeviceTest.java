package com.homeautomation.model;

public class DeviceTest {

    public static void main(String[] args) {

        Device light = new Light(1, "Living Room Light", false);
        Device fan = new Fan(2, "Bedroom Fan", false);
        Device ac = new AirConditioner(3, "Bedroom AC", false);

        light.turnOn();
        fan.turnOn();
        ac.turnOn();

        System.out.println("Light status: " + light.isStatus());
        System.out.println("Fan status: " + fan.isStatus());
        System.out.println("AC status: " + ac.isStatus());
    }
}