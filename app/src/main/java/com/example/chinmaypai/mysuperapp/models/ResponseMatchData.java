
package com.example.chinmaypai.mysuperapp.models;

import java.util.ArrayList;
import java.util.List;

public class ResponseMatchData {

    private boolean cache;
    private List<MatchData> data = new ArrayList<MatchData>();


    /**
     * 
     * @return
     *     The cache
     */
    public boolean isCache() {
        return cache;
    }

    /**
     * 
     * @param cache
     *     The cache
     */
    public void setCache(boolean cache) {
        this.cache = cache;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<MatchData> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(List<MatchData> data) {
        this.data = data;
    }



}
