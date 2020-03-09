package com.example.edamamapp.utils;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.edamamapp.R;
import com.example.edamamapp.model.Hits;
import com.example.edamamapp.model.Recipe;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {
    private Activity activity;
    private final LayoutInflater mInflater;
    private List<Hits> hitsList;

    public RecipeListAdapter(Activity activity) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
    }

    public void setRecipes(List<Hits> hitsList) {
        this.hitsList = hitsList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_food;
        private ChipGroup food_label_group;
        private MaterialTextView txt_recipe_title, txt_calories, txt_ingredient_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_food = itemView.findViewById(R.id.img_food);
            food_label_group = itemView.findViewById(R.id.food_label_group);
            txt_recipe_title = itemView.findViewById(R.id.txt_recipe_title);
            txt_calories = itemView.findViewById(R.id.txt_calories);
            txt_ingredient_count = itemView.findViewById(R.id.txt_ingredient_count);
        }
    }

    @NonNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.food_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder holder, int position) {
        if (hitsList != null) {
            Hits hits = hitsList.get(position);
            Recipe recipe = hits.getRecipe();
            holder.txt_recipe_title.setText(recipe.getLabel());
            Glide.with(activity).load(recipe.getImage()).centerCrop().into(holder.img_food);
            holder.txt_ingredient_count.setText(recipe.getIngredientLines().size() + " ingredients");
        }
    }

    @Override
    public int getItemCount() {
        if (hitsList != null) {
            return hitsList.size();
        } else {
            return 0;
        }
    }
}
