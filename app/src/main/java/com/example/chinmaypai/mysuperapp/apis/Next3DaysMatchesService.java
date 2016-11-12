package com.example.chinmaypai.mysuperapp.apis;

import com.example.chinmaypai.mysuperapp.models.Match;
import com.example.chinmaypai.mysuperapp.models.ResponseMatchData;
import com.example.chinmaypai.mysuperapp.models.ResponseMatches;
import com.example.chinmaypai.mysuperapp.models.ResponseScore;
import com.example.chinmaypai.mysuperapp.models.ScoreData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Chinmay Pai on 16-10-2016.
 */

public interface Next3DaysMatchesService {
    //@GET("api/matches") Call<ResponseMatches> listMatches();

    @GET("api/cricket") Call<ResponseMatchData> listMatchData();
    @GET("/api/cricketScore") Call<ResponseScore> fetchScore(@Query("unique_id") String uid);
}
