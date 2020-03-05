package com.example.edamamapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hits {
    @SerializedName("recipe")
    @Expose
    private Recipe recipe;
}
