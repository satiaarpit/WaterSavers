package com.example.asatia.watersavers.SurveyPackage;

/**
 * Created by asatia on 11/25/2015.
 */
public class GoalListItem {
    private String milestone;
    private String minScore;
    private boolean lock;
    private int medal;
    private int coupons[];

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getMinScore() {
        return minScore;
    }

    public void setMinScore(String minScore) {
        this.minScore = minScore;
    }

    public boolean getLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public int getCoupons(int i) {
        return coupons[i];
    }

    public void setCoupons(int[] coupons) {
        this.coupons=new int[coupons.length];
        for(int i=0;i<coupons.length;i++) {
            this.coupons[i] = coupons[i];
        }
    }

    public int getMedal() {
        return medal;
    }

    public void setMedal(int medal) {
        this.medal = medal;
    }
}
