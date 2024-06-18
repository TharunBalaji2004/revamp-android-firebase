package com.example.revamp_redbus_clone.model;

public class Bus {
    String busName;
    String busType;
    String startingPoint;
    String destination;
    String startingTime;
    String reachingTime;
    String duration;
    String price;

    public Bus() {

    }

    public Bus(String busName, String busType, String startingPoint, String destination, String startingTime, String reachingTime, String duration, String price) {
        this.busName = busName;
        this.busType = busType;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.startingTime = startingTime;
        this.reachingTime = reachingTime;
        this.duration = duration;
        this.price = price;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getReachingTime() {
        return reachingTime;
    }

    public void setReachingTime(String reachingTime) {
        this.reachingTime = reachingTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
