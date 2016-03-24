package com.example.asatia.watersavers.SurveyPackage;

/**
 * Created by asatia on 12/4/2015.
 */
public class MapListItem {
    private String user;
    private int score;
    private double lat;
    private double lon;
    private boolean isUser;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        this.isUser = user;
    }
}
