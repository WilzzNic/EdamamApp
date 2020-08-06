package com.example.edamamapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.edamamapp.model.Recipe
import com.example.edamamapp.utils.RecipeDetailsViewPager
import com.google.android.material.appbar.CollapsingToolbarLayout


class RecipeDetailsActivity : AppCompatActivity() {
    private var imgViewFood: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        val collapsingToolbar: CollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar)
        imgViewFood = findViewById(R.id.img_view_food)

        val i = intent
        val recipe: Recipe = i.getSerializableExtra("Recipe") as Recipe

        collapsingToolbar.title = recipe.label

        Glide.with(this)
                .load(recipe.image)
                .centerCrop()
                .into(imgViewFood!!)

        val viewPager : ViewPager = findViewById(R.id.viewpager)
        viewPager.adapter = RecipeDetailsViewPager(supportFragmentManager, this)
    }
}
