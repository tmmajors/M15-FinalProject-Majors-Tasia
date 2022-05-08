package com.company.FinalProject.Weather;

public class WeatherResponse {

    private String name;
    private WeatherCoordinates coordinates;
    private Main main;
    private WeatherConditions[] weatherConditions;
    private String base;
    private int visibility;
    private int cod;
    private String message;
    private SystemInfo systemInfo;
    private int timezone;
    private int id;
    private int dt;
    private Wind wind;
    private Clouds clouds;

    //getters + setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WeatherCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(WeatherCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public WeatherConditions[] getWeatherConditions() {
        return weatherConditions;
    }

    public void setWeatherConditions(WeatherConditions[] weatherConditions) {
        this.weatherConditions = weatherConditions;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo systemInfo) {
        this.systemInfo = systemInfo;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

}
