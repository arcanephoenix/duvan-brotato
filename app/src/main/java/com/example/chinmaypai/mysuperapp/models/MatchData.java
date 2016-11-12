
package com.example.chinmaypai.mysuperapp.models;


public class MatchData {

    private String uniqueId;
    private String description;
    private String title;

    /**
     * 
     * @return
     *     The uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * 
     * @param uniqueId
     *     The unique_id
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
