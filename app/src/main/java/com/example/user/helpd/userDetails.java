package com.example.user.helpd;

public class userDetails {
    private String name;
    private String number;
    private String address;
    private String helpPref;
    private String timings;
    private UserPref preference= new UserPref();


    public userDetails() {
    }

    public userDetails(String name, String number, String address, String helpPref, String timings, UserPref preference) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.helpPref = helpPref;
        this.timings = timings;
        this.preference= preference;
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

    public String getHelpPref() {
        return helpPref;
    }

    public void setHelpPref(String helpPref) {
        this.helpPref = helpPref;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setPreference(UserPref preference) {
        this.preference = preference;
    }

    public UserPref getPreference() {
        return preference;
    }
}