package com.homeautomation.service;

public class DeviceMonitor extends Thread {

    private String deviceName;

    public DeviceMonitor(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public void run() {
        monitorDevice();
    }

    public synchronized void monitorDevice() {

        System.out.println(
                "Monitoring started for: " + deviceName
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Monitoring interrupted.");
        }

        System.out.println(
                "Monitoring completed for: " + deviceName
        );
    }
}