package com.example.chinmaypai.mysuperapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Chinmay Pai on 15-10-2016.
 */

public class Match {
    public long unique_id=0;
    public String team_1="";
    public String team_2="";
    public Score team_1_first = new Score();
    public Score team_1_second = new Score();
    public Score team_2_first = new Score();
    public Score team_2_second = new Score();
    public boolean isTest=false;
    public long totalOvers=0;


}
