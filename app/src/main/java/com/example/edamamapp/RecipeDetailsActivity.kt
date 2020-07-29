package com.example.edamamapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edamamapp.model.Recipe
import com.google.android.material.appbar.CollapsingToolbarLayout


class RecipeDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val collapsingToolbar : CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.title = "Recipe Details"

        val i = intent
        val recipe: Recipe = i.getSerializableExtra("Recipe") as Recipe
    }
}
