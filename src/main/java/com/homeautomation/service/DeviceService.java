package com.homeautomation.service;

import com.homeautomation.exception.DeviceException;
import com.homeautomation.model.Device;

/**
 * Provides business logic for controlling home automation devices.
 * Validates the device before performing ON/OFF operations.
 */
public class DeviceService {

    // Turns a device ON after checking that the device exists
    public void turnOnDevice(Device device) throws DeviceException {

        if (device == null) {
            throw new DeviceException("Device not found.");
        }

        device.turnOn();
    }

    // Turns a device OFF after checking that the device exists
    public void turnOffDevice(Device device) throws DeviceException {

        if (device == null) {
            throw new DeviceException("Device not found.");
        }

        device.turnOff();
    }
}