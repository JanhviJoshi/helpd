package com.example.user.helpd;

public class UserPref {

    private String helpPref;
    private int timings;

    public UserPref() {}

    public UserPref(String helpPref, int timings){
        this.helpPref= helpPref;
        this.timings= timings;
    }

    public int getTimings() {
        return timings;
    }

    public String getHelpPref() {
        return helpPref;
    }

    public void setTimings(int timings) {
        this.timings = timings;
    }

    public void setHelpPref(String helpPref) {
        this.helpPref = helpPref;
    }
}
