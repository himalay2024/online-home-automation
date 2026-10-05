package com.homeautomation.service;

import com.homeautomation.model.Device;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

    private List<Device> devices;

    public DeviceManager() {
        devices = new ArrayList<>();
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public int getDeviceCount() {
        return devices.size();
    }
}