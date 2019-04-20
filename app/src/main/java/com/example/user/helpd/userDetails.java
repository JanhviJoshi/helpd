package com.example.user.helpd;

public class UserDetails {
    private String name;
    private String number;
    private String address;
    private String city;
    private String email;
    private double latitude;
    private double longitude;
    private String helperid;

    private String services;
    private int timings;



    public UserDetails() {
    }

   /* public UserDetails(String name, String number,  String email, double latitude, double longitude){
        this.name= name;
        this.number= number;
        this.email= email;
        this.latitude= latitude;
        this.longitude= longitude;
    }

    public UserDetails(String services, int timings){
        this.services=services;
        this.timings=timings;
    } */

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getServices() {
        return services;
    }

    public int getTimings() {
        return timings;
    }

    public String getHelperid() {
        return helperid;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTimings(int timings) {
        this.timings = timings;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setHelperid(String helperid) {
        this.helperid = helperid;
    }
}
