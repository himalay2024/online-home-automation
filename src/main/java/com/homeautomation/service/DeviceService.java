package com.homeautomation.service;

import com.homeautomation.exception.DeviceException;
import com.homeautomation.model.Device;

public class DeviceService {

    public void turnOnDevice(Device device) throws DeviceException {

        if (device == null) {
            throw new DeviceException("Device not found.");
        }

        device.turnOn();
    }

    public void turnOffDevice(Device device) throws DeviceException {

        if (device == null) {
            throw new DeviceException("Device not found.");
        }

        device.turnOff();
    }
}