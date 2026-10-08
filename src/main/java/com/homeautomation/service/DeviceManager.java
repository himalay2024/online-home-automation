package com.homeautomation.service;

import com.homeautomation.model.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of devices used by the application.
 * Uses Java Collections and Generics to store Device objects.
 */
public class DeviceManager {

    // Stores all devices managed by the application
    private List<Device> devices;

    // Initializes an empty device list
    public DeviceManager() {
        devices = new ArrayList<>();
    }

    // Adds a device to the collection
    public void addDevice(Device device) {
        devices.add(device);
    }

    // Returns the list of all devices
    public List<Device> getDevices() {
        return devices;
    }

    // Returns the total number of devices
    public int getDeviceCount() {
        return devices.size();
    }
}