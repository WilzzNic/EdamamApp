package com.example.edamamapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

// Implement Serializable due to Recipe for this class to be serialize
public class Ingredient implements Serializable {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("weight")
    @Expose
    private Double weight;
}
