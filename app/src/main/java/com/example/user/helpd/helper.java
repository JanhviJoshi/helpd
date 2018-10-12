package com.example.user.helpd;

public class helper {

    private String services;
    private String timings;
    private String name;

    public helper() {}

    public helper(String services, String timings, String name)
    {
        this.services= services;
        this.timings= timings;
        this.name= name;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getTimings() {
        return timings;
    }

    public String getName() {
        return name;
    }

    public String getServices() {
        return services;
    }
}
