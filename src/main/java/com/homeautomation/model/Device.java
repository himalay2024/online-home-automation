package com.homeautomation.model;

import com.homeautomation.interfaces.Controllable;

public class Device implements Controllable {

    private int id;
    private String name;
    private String type;
    private boolean status;

    public Device() {
    }

    public Device(int id, String name, String type, boolean status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void turnOn() {
        status = true;
    }

    public void turnOff() {
        status = false;
    }
}