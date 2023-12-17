package com.example.prueba05;

import java.io.Serializable;

import java.io.Serializable;

public class SerializableLatLng implements Serializable {
    private double latitude;
    private double longitude;

    public SerializableLatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "SerializableLatLng{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

