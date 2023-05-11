package com.WeatherData;

import java.util.ArrayList;
import java.util.Map;

import static com.helperclass.TimeHelper.convertUnixToLocal;

//https://openweathermap.org/current#geocoding - Documentation for weather data
public class WeatherInfo {
    Coorrdinates coorrdinates;

    String name;

    String weatherDescription, weatherMain, iconCode;
    double temperature, feelsLike, minTemp, maxTemp, humidityPercentage;
    double pressure;
    double visibility;
    double windSpeed, windDeg;
    double cloudsAll;
    long currentTimeInUnixSeconds, timezone, sunriseTimeInUnixSeconds, sunsetTimeInUnixSeconds;
    String currentTimeDateTimeZone, sunriseTime, sunsetTime;
    Map<String, Object> weatherInfo;

    public Map<String, Object> getStringObjectMap(String s) {
        return (Map<String, Object>) this.weatherInfo.get(s);
    }

    public WeatherInfo(Map<String, Object> weatherInfo) {
        this.weatherInfo = weatherInfo;

        name = (String) weatherInfo.get("name");
        Map<String, Object> coord = getStringObjectMap("coord");
        Map<String, Object> weather = ((ArrayList<Map<String, Object>>) weatherInfo.get("weather")).get(0);
        Map<String, Object> mainInfo = getStringObjectMap("main");
        Map<String, Object> wind = getStringObjectMap("wind");
        Map<String, Object> clouds = getStringObjectMap("clouds");
        Map<String, Object> sys = getStringObjectMap("sys");

        coorrdinates = new Coorrdinates((Double) coord.get("lat"), (Double) coord.get("lon"));

        weatherDescription = (String) weather.get("description");
        weatherMain = (String) weather.get("main");
        iconCode = (String) weather.get("icon");

        temperature = (double) mainInfo.get("temp");
        feelsLike = (double) mainInfo.get("feels_like");
        minTemp = (double) mainInfo.get("temp_min");
        maxTemp = (double) mainInfo.get("temp_max");
        pressure = (double) mainInfo.get("pressure");
        humidityPercentage = (double) mainInfo.get("humidity");

        visibility = (double) weatherInfo.get("visibility");

        windDeg = (double) wind.get("deg");
        windSpeed = (double) wind.get("speed");

        currentTimeInUnixSeconds = (long) ((double) weatherInfo.get("dt"));
        timezone = (long) ((double) weatherInfo.get("timezone"));
        sunriseTimeInUnixSeconds = (long) ((double) sys.get("sunrise"));
        sunsetTimeInUnixSeconds = (long) ((double) sys.get("sunset"));

        currentTimeDateTimeZone = convertUnixToLocal(currentTimeInUnixSeconds, timezone);
        sunriseTime = convertUnixToLocal(sunriseTimeInUnixSeconds, timezone).split(" ")[1];
        sunsetTime = convertUnixToLocal(sunsetTimeInUnixSeconds, timezone).split(" ")[1];

    }
}
