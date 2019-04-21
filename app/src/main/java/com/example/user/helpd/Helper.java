package com.example.user.helpd;

public class Helper {

    private String services;
    private int timings;
    private String name;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String price) {
        this.contact = price;
    }

    private int rate;
    private String fee;
    private String contact;
    private int id;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Helper() {}

    public Helper(String services, int timings, String name)
    {
        this.services= services;
        this.timings= timings;
        this.name= name;
    }

    public void setTimings(int timings) {
        this.timings = timings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public int getTimings() {
        return timings;
    }

    public String getName() {
        return name;
    }

    public String getServices() {
        return services;
    }
}
