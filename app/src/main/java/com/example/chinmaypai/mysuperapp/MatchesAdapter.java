package com.example.chinmaypai.mysuperapp;

import android.content.Context;
import android.support.v4.util.LongSparseArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chinmaypai.mysuperapp.apis.Next3DaysMatchesService;
import com.example.chinmaypai.mysuperapp.events.ScoreEvent;
import com.example.chinmaypai.mysuperapp.models.Match;
import com.example.chinmaypai.mysuperapp.models.ScoreData;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chinmay Pai.
 */

public class MatchesAdapter  extends RecyclerView.Adapter {
    Retrofit retrofit;
    Next3DaysMatchesService next3DaysMatchesService;
    Callback callback;
    List<ScoreData> scoreDataList;
    Context context;
    List<Match> allmatches = new ArrayList<>();
    List<ScoreData> allScores = new ArrayList<>();
    LongSparseArray<ScoreData> scoresMap = new LongSparseArray<>();
    ClickManager clickManager;

    public void setClickManager(ClickManager clickManager) {
        this.clickManager = clickManager;
    }

    @Override
    public long getItemId(int position) {
        return allmatches.get(position).unique_id;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         context = parent.getContext();
        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.match_item,parent,false);
        MatchViewHolder holder = new MatchViewHolder(view);
        return holder;


    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MatchViewHolder)holder).bindMatchWithView(allmatches.get(position));

    }

    public void addMatches(List<Match> mlist)
    {

            allmatches.addAll(mlist);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allmatches.size();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onScoreEvent(ScoreEvent scoreEvent) {
        scoresMap.put(scoreEvent.matchID, scoreEvent.scoreData);
        for(int i = 0; i < allmatches.size(); i++) {
            if(allmatches.get(i).unique_id == scoreEvent.matchID) {
                notifyItemChanged(i);
                break;
            }
        }
    }

    public class MatchViewHolder extends RecyclerView.ViewHolder {

        TextView team1, team2, date, score;
        View parent;

        public MatchViewHolder(View itemView) {
            super(itemView);
            parent = itemView;
            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
            date = (TextView) itemView.findViewById(R.id.date);
            //// TODO: 07-11-2016 initialize score view
        }

        public void bindMatchWithView(Match match) {
            team1.setText(match.team_1);
            team2.setText(match.team_2);
            date.setText(match.team_1_first.runs+"/"+match.team_1_first.wickets);
            //score.setText(scoresMap.get(match.unique_id).getScore()); scoremap?
            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickManager.onMatchClicked(match);
                }
            });*/
        }


    }

    public interface ClickManager {
        public void onMatchClicked(Match match);
    }
}
