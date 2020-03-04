package com.example.edamamapp.repository.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Hits {
    @SerializedName("recipe")
    @Expose
    private List<Recipe> recipeList;
}
