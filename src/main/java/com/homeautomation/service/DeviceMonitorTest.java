package com.homeautomation.service;

public class DeviceMonitorTest {

    public static void main(String[] args) {

        DeviceMonitor lightMonitor =
                new DeviceMonitor("Living Room Light");

        DeviceMonitor fanMonitor =
                new DeviceMonitor("Bedroom Fan");

        lightMonitor.start();
        fanMonitor.start();
    }
}