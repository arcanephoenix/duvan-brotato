package com.example.chinmaypai.mysuperapp.models;

/**
 * Created by Chinmay Pai on 10-11-2016.
 */

public class Score {
        public long runs=0;
        public long wickets=0;
        public long balls=0;
        long[] overs() {
            long nOvers=balls/6;
            long ballsCurrentOver=balls%6;
            long[] ovs = {nOvers, ballsCurrentOver};
            return (ovs);
        }
}
