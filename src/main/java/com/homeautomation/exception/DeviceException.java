package com.homeautomation.exception;

/**
 * Custom exception used for handling device-related errors
 * in the home automation system.
 */
public class DeviceException extends Exception {

    // Creates an exception with a specific error message
    public DeviceException(String message) {
        super(message);
    }
}