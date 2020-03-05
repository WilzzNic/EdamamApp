package com.example.edamamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.edamamapp.callback.Callback;
import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.utils.RecipeTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeTask.searchRecipe(new Callback<SearchResponse>() {
            @Override
            public void returnResult(SearchResponse searchResponse) {

            }

            @Override
            public void returnError(String message) {

            }
        });
    }
}
