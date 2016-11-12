
package com.example.chinmaypai.mysuperapp;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.chinmaypai.mysuperapp.apis.Next3DaysMatchesService;
import com.example.chinmaypai.mysuperapp.models.Match;
import com.example.chinmaypai.mysuperapp.models.MatchData;
import com.example.chinmaypai.mysuperapp.models.ResponseMatchData;
import com.example.chinmaypai.mysuperapp.models.ResponseMatches;
import com.example.chinmaypai.mysuperapp.models.ResponseScore;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity /*implements MatchesAdapter.ClickManager*/ {
    private static final String TAG = "MainActivity";
    Retrofit retrofit;
    RecyclerView rcv;
    SwipeRefreshLayout swipeRefreshLayout;
    Next3DaysMatchesService next3days;
    List<MatchData> responseMatchesData;
    MatchesAdapter matchesAdapter;
    Callback callback;
    List<Match> matchesData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = (RecyclerView) findViewById(R.id.recview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);
        matchesAdapter=new MatchesAdapter();
        //matchesAdapter.setClickManager(this);
        retrofit = new Retrofit.Builder().baseUrl("http://cricapi.com").addConverterFactory(GsonConverterFactory.create()).build();
        callback = new Callback<ResponseMatchData>() {
            @Override
            public void onResponse(Call<ResponseMatchData> call, Response<ResponseMatchData> response) {
                Log.d(TAG, "onResponse: " + response.isSuccessful() + response.message());
                responseMatchesData=(response.body()).getData();
                int i=0;
                for (MatchData matchData : responseMatchesData) {

                    matchesData.add(MatchParser.parseMatch(matchData));
                    Log.d(TAG,"allmatchesdata is:"+matchesData.get(i++).team_2_first.runs);
                }

                matchesAdapter.addMatches(matchesData);
                matchesAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);


            }

            @Override
            public void onFailure(Call<ResponseMatchData> call, Throwable t) {
                t.printStackTrace();

            }
        };
        next3days = retrofit.create(Next3DaysMatchesService.class);

        rcv.setAdapter(matchesAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        fetchMatches();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMatches();
            }
            // aaaargh light!!!!!
        });

    }

    void fetchMatches() {

        Call<ResponseMatchData> matchdata = next3days.listMatchData();
        Call<ResponseScore> scoreData = next3days.fetchScore("1542846");
        matchdata.enqueue(callback);
    }
/*
    @Override
    public void onMatchClicked(Match match) {
       // Intent intent = new Intent(this,dfsfofjgmhj); // TODO: 07-11-2016 activity
       // Bundle
        // TODO: 07-11-2016 make Match parcelable, use Bundle, pass objects around.
        intent.putExtra("matchId", match.unique_id);
        startActivity(intent);
    }*/
}