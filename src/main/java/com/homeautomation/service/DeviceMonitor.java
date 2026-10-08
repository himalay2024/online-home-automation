package com.homeautomation.service;

/**
 * Monitors a home automation device in a separate thread.
 *
 * This class demonstrates multithreading by extending the Thread class.
 * The synchronized monitoring method ensures that only one thread
 * can execute the monitoring operation on the same monitor object
 * at a time.
 */
public class DeviceMonitor extends Thread {

    // Stores the name of the device being monitored
    private String deviceName;

    /**
     * Creates a device monitor for the specified device.
     *
     * @param deviceName name of the device to monitor
     */
    public DeviceMonitor(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * Entry point of the monitoring thread.
     * The start() method of Thread automatically calls this method.
     */
    @Override
    public void run() {
        monitorDevice();
    }

    /**
     * Performs the device monitoring operation.
     *
     * The synchronized keyword prevents multiple threads from
     * executing this method on the same object simultaneously.
     */
    public synchronized void monitorDevice() {

        System.out.println(
                "Monitoring started for: " + deviceName
        );

        try {
            // Simulates the time taken to monitor the device
            Thread.sleep(1000);

        } catch (InterruptedException e) {

            // Restore the interrupted status of the current thread
            Thread.currentThread().interrupt();

            System.out.println("Monitoring interrupted.");
        }

        System.out.println(
                "Monitoring completed for: " + deviceName
        );
    }
}