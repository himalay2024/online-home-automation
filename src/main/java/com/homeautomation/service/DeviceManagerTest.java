package com.homeautomation.service;

import com.homeautomation.model.AirConditioner;
import com.homeautomation.model.Device;
import com.homeautomation.model.Fan;
import com.homeautomation.model.Light;

public class DeviceManagerTest {

    public static void main(String[] args) {

        DeviceManager manager = new DeviceManager();

        Device light = new Light(1, "Living Room Light", false);
        Device fan = new Fan(2, "Bedroom Fan", false);
        Device ac = new AirConditioner(3, "Bedroom AC", false);

        manager.addDevice(light);
        manager.addDevice(fan);
        manager.addDevice(ac);

        System.out.println("Total devices: " + manager.getDeviceCount());

        for (Device device : manager.getDevices()) {
            System.out.println(device.getName() + " - " + device.getType());
        }
    }
}