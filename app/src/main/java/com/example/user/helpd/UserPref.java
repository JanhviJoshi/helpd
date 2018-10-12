package com.example.user.helpd;

public class UserPref {

    private String helpPref;
    private String timings;

    public UserPref() {}

    public UserPref(String helpPref, String timings){
        this.helpPref= helpPref;
        this.timings= timings;
    }

    public String getTimings() {
        return timings;
    }

    public String getHelpPref() {
        return helpPref;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public void setHelpPref(String helpPref) {
        this.helpPref = helpPref;
    }
}
