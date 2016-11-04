package com.example.nicklasandersen.fishcatcher;

/**
 * Created by nicklasandersen on 04-11-16.
 */
public class Catches {
    private int id;
    private String angler_name;
    private String datetime;
    private String fishing_method;
    private String breed;
    private String length;
    private String weight;
    private String weather;
    private String location;
    private double latitude;
    private double longitude;

    public Catches(int id, String angler_name, String datetime, String fishing_method, String breed, String length, String weight, String weather, String location, double latitude, double longitude) {
        this.id = id;
        this.angler_name = angler_name;
        this.datetime = datetime;
        this.fishing_method = fishing_method;
        this.breed = breed;
        this.length = length;
        this.weight = weight;
        this.weather = weather;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAngler_name() {
        return angler_name;
    }

    public void setAngler_name(String angler_name) {
        this.angler_name = angler_name;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFishing_method() {
        return fishing_method;
    }

    public void setFishing_method(String fishing_method) {
        this.fishing_method = fishing_method;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Catches{" +
                "angler_name='" + angler_name + '\'' +
                '}';
    }
}
