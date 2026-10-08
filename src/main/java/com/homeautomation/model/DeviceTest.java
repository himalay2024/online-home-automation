package com.homeautomation.model;

/**
 * Test class used to demonstrate the working of different
 * home automation device types.
 *
 * This class demonstrates inheritance and polymorphism by
 * storing Light, Fan and AirConditioner objects in Device
 * references and calling their common methods.
 */
public class DeviceTest {

    /**
     * Main method used to test device creation and control.
     */
    public static void main(String[] args) {

        // Polymorphism: child class objects are referenced
        // using the parent Device type.
        Device light =
                new Light(1, "Living Room Light", false);

        Device fan =
                new Fan(2, "Bedroom Fan", false);

        Device ac =
                new AirConditioner(3, "Bedroom AC", false);

        // Turn all devices ON using the common Device interface.
        light.turnOn();
        fan.turnOn();
        ac.turnOn();

        // Display the current status of each device.
        System.out.println(
                "Light status: " + light.isStatus());

        System.out.println(
                "Fan status: " + fan.isStatus());

        System.out.println(
                "AC status: " + ac.isStatus());
    }
}