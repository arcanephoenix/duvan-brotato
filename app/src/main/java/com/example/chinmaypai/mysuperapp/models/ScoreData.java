package com.example.chinmaypai.mysuperapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chinmay Pai on 20-10-2016.
 */

public class ScoreData {
    private boolean matchStarted;
    @SerializedName("team-2")
    private String team2;
    @SerializedName("team-1")
    private String team1;
    @SerializedName("innings-requirement")
    private String battingTeamRequired;
    private String score;



    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }

    public void setMatchStarted(boolean matchStarted) {
        this.matchStarted = matchStarted;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getBattingTeamRequired() {
        return battingTeamRequired;
    }

    public void setBattingTeamRequired(String battingTeamRequired) {
        this.battingTeamRequired = battingTeamRequired;
    }

}
