package com.example.chinmaypai.mysuperapp.events;

import com.example.chinmaypai.mysuperapp.models.ScoreData;

/**
 * Created by Chinmay Pai on 07-11-2016.
 */

public class ScoreEvent {
    public ScoreData scoreData;
    public long matchID;

    public ScoreEvent(ScoreData scoreData, long matchID) {
        this.scoreData = scoreData;
        this.matchID = matchID;
    }


}
