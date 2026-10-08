package com.homeautomation.interfaces;

/**
 * Defines the basic control operations for home automation devices.
 * Classes implementing this interface must provide methods
 * to turn a device ON and OFF.
 */
public interface Controllable {

    // Turns the device ON
    void turnOn();

    // Turns the device OFF
    void turnOff();
}