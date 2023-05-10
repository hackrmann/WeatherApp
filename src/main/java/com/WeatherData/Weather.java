package com.WeatherData;

import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Weather {

    private final static String API_KEY = "bf7a9e6aaf56e246ea97c1e932075910";
    String location;
    Coorrdinates defaultCoordinates; //delete this

    String weatherUnits;

    final int searchQueryLimit = 3;

    public Weather() {
        this.location = "New York";
        this.defaultCoordinates = new Coorrdinates(40.7143, -74.006);
        this.weatherUnits = "metric";
    }

    public String dataUrlBuilder(Coorrdinates coorrdinates) {
        return "http://api.openweathermap.org/data/2.5/weather?lat=" + defaultCoordinates.latitude +
                "&lon=" + defaultCoordinates.longitude + "&appid=" + API_KEY
                + "&units=" + weatherUnits;
    }

    public String searchUrlBuilder(String searchQuery) {
        return "http://api.openweathermap.org/geo/1.0/direct?q=" + searchQuery +
                "&limit=" + searchQueryLimit + "&appid=" + API_KEY;
    }

    public static Map<String, Object> jsonToMap(String s) {
        Map<String, Object> map = new Gson().fromJson(s, new TypeToken<HashMap<String, Object>>() {
        }.getType());
        return map;
    }

    public static ArrayList<Map<String, Object>> jsonToArrayList(String s) {
        Type mapListType = new TypeToken<ArrayList<Map<String, Object>>>() {
        }.getType();
        ArrayList<Map<String, Object>> listOfLocations = new Gson().fromJson(s, mapListType);

        return listOfLocations;
    }

    public ArrayList<SearchTermLocation> getSearchResults() {
        ArrayList<SearchTermLocation> searchTermLocations = new ArrayList<>();
        try {
            StringBuilder result = new StringBuilder();
            Weather weather = new Weather();
            URL url = new URL(weather.searchUrlBuilder("New York"));
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            System.out.println(result);

            ArrayList<Map<String, Object>> resultMapping = jsonToArrayList(result.toString());

            for (Map<String, Object> location : resultMapping) {
                searchTermLocations.add(new SearchTermLocation(
                        new Coorrdinates((Double) location.get("lat"), (Double) location.get("lon")),
                        (String) location.get("name"),
                        (String) location.get("country"),
                        (String) location.get("state")));
            }

            System.out.println(resultMapping.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return searchTermLocations;
    }

    public void getWeatherData() {
        try {
            StringBuilder result = new StringBuilder();
            Weather weather = new Weather();
            URL url = new URL(weather.dataUrlBuilder(defaultCoordinates));
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
            System.out.println(result);

            Map<String, Object> resultMapping = jsonToMap(result.toString());

            System.out.println(resultMapping.get("weather"));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Weather weather = new Weather();
        weather.getSearchResults();
        System.out.println("-----------------");
        weather.getWeatherData();
    }
}
