package com.example.asatia.watersavers.SurveyPackage;

import android.location.Location;

import java.util.ArrayList;

/**
 * Created by asatia on 11/28/2015.
 */
public class WaterData {
    private String userName;
    private String firstName;
    private String lastName;
    private double latitude;
    private double longitude;
    private int familySize;
    private int showerHeadType;
    private int showerDuration;
    private int showerFrequency;
    private int dishWasherType;
    private int dishWasherFrequency;
    private int flushType;
    private int flushFrequency;
    private int miscellaneous;
    private int sprinklerNumber;
    private int sprinklerDuration;
    private int washingMachineType;
    private int washingMachineFrequency;
    private long consumption;
    private long averageConsumption;
    private ArrayList<String> tips;
    private long score=0;
    private long usage;
    private long waterSaved;
    private ArrayList<String> stars;
    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public int getShowerHeadType() {
        return showerHeadType;
    }

    public void setShowerHeadType(int showerHeadType) {
        this.showerHeadType = showerHeadType;
    }

    public int getShowerDuration() {
        return showerDuration;
    }

    public void setShowerDuration(int showerDuration) {
        this.showerDuration = showerDuration;
    }

    public int getShowerFrequency() {
        return showerFrequency;
    }

    public void setShowerFrequency(int showerFrequency) {
        this.showerFrequency = showerFrequency;
    }

    public int getDishWasherType() {
        return dishWasherType;
    }

    public void setDishWasherType(int dishWasherType) {
        this.dishWasherType = dishWasherType;
    }

    public int getDishWasherFrequency() {
        return dishWasherFrequency;
    }

    public void setDishWasherFrequency(int dishWasherFrequency) {
        this.dishWasherFrequency = dishWasherFrequency;
    }

    public int getFlushType() {
        return flushType;
    }

    public void setFlushType(int flushType) {
        this.flushType = flushType;
    }

    public int getFlushFrequency() {
        return flushFrequency;
    }

    public void setFlushFrequency(int flushFrequency) {
        this.flushFrequency = flushFrequency;
    }

    public int getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(int miscellaneous) {
        this.miscellaneous = miscellaneous;
    }

    public int getSprinklerNumber() {
        return sprinklerNumber;
    }

    public void setSprinklerNumber(int sprinklerNumber) {
        this.sprinklerNumber = sprinklerNumber;
    }

    public int getSprinklerDuration() {
        return sprinklerDuration;
    }

    public void setSprinklerDuration(int sprinklerDuration) {
        this.sprinklerDuration = sprinklerDuration;
    }

    public int getWashingMachineType() {
        return washingMachineType;
    }

    public void setWashingMachineType(int washingMachineType) {
        this.washingMachineType = washingMachineType;
    }

    public int getWashingMachineFrequency() {
        return washingMachineFrequency;
    }

    public void setWashingMachineFrequency(int washingMachineFrequency) {
        this.washingMachineFrequency = washingMachineFrequency;
    }

    public long getConsumption() {
        return consumption;
    }

    public void setConsumption(long consumption) {
        this.consumption = consumption;
    }

    public long getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(long averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public ArrayList<String> getTip() {
        return tips;
    }

    public void setTip(ArrayList<String> tip) {
        this.tips=new ArrayList<String>();
        for(int i=0;i<tip.size();i++)   {
            this.tips.add(tip.get(i));
        }
    }

    public ArrayList<String> getStars() {
        return stars;
    }

    public void setStars(ArrayList<String> stars) {
        this.stars=new ArrayList<String>();
        for(int i=0;i<stars.size();i++)   {
            this.stars.add(stars.get(i));
        }
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getUsage() {
        return usage;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }

    public long getWaterSaved() {
        return waterSaved;
    }

    public void setWaterSaved(long waterSaved) {
        this.waterSaved = waterSaved;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
