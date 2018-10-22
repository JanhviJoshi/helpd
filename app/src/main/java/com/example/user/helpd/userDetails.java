package com.example.user.helpd;

public class userDetails {
    private String name;
    private String number;
    private String address;
    private String landmark;
    private String city;
    private String pincode;


    public userDetails() {
    }

    public userDetails(String name, String number, String address,String landmark, String city, String pincode) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.landmark = landmark;
        this.city = city;
        this.pincode= pincode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getPincode() {
        return pincode;
    }
}