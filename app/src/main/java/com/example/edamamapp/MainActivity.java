package com.example.edamamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.viewmodel.RecipeViewModel;

public class MainActivity extends AppCompatActivity {
    private RecipeViewModel mRecipeViewModel;
    private TextView txtFoodName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFoodName = findViewById(R.id.txtFoodName);

        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mRecipeViewModel.getSearchResults().observe(this, new Observer<SearchResponse>() {
            @Override
            public void onChanged(SearchResponse searchResponse) {
                txtFoodName.setText(searchResponse.getHitsList().get(0).getRecipe().getLabel());
            }
        });
    }
}
