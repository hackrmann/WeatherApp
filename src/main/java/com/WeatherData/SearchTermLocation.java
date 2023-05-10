package com.WeatherData;

public class SearchTermLocation {
    public Coorrdinates coorrdinates;
    public String localName;
    public String country;
    public String state;

    public SearchTermLocation(Coorrdinates coorrdinates, String localName, String country, String state) {
        this.coorrdinates = new Coorrdinates(coorrdinates);
        this.localName = localName;
        this.country = country;
        this.state = state;
    }
}
