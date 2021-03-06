package com.example.edamamapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {
    @SerializedName("q")
    @Expose
    private String q;

    @SerializedName("from")
    @Expose
    private int from;

    @SerializedName("to")
    @Expose
    private int to;

    @SerializedName("more")
    @Expose
    private boolean more;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("hits")
    @Expose
    private List<Hits> hitsList = null;

    public String getQ() {
        return q;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public boolean isMore() {
        return more;
    }

    public int getCount() {
        return count;
    }

    public List<Hits> getHitsList() {
        return hitsList;
    }
}
