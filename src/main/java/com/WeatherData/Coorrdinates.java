package com.WeatherData;

public class Coorrdinates {
    public Coorrdinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Coorrdinates(Coorrdinates coorrdinates) {
        this(coorrdinates.latitude, coorrdinates.longitude);
    }

    public double latitude, longitude;
}
