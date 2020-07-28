package com.example.edamamapp.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.edamamapp.R;
import com.example.edamamapp.model.Hits;
import com.example.edamamapp.model.Recipe;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_LOADING = 1;
    private static final int VIEW_TYPE_ITEM = 0;
    private Activity activity;
    private final LayoutInflater mInflater;
    private List<Hits> hitsList = new ArrayList<>();
    private final OnItemClickListener mOnClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public RecipeListAdapter(Activity activity, OnItemClickListener listener) {
        this.activity = activity;
        mInflater = LayoutInflater.from(activity);
        mOnClickListener = listener;
    }

    public Recipe getRecipe(int position) {
        Hits hits = hitsList.get(position);
        return hits.getRecipe();
    }

    public void resetList() {
        hitsList.clear();
        this.notifyDataSetChanged();
    }

    public void setRecipes(List<Hits> hits) {
        if (hits.size() == 0) {
            hitsList = hits;
            notifyDataSetChanged();
        } else {
            int positionStart = getItemCount();
            hitsList.addAll(hits);
            notifyItemRangeInserted(positionStart, hits.size());
        }

    }

    class ViewHolderRow extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img_food;
        private MaterialTextView txt_recipe_title, txt_calories, txt_ingredient_count;
        private FlexboxLayout label_group;

        public ViewHolderRow(@NonNull View itemView) {
            super(itemView);
            img_food = itemView.findViewById(R.id.img_food);
            txt_recipe_title = itemView.findViewById(R.id.txt_recipe_title);
            txt_calories = itemView.findViewById(R.id.txt_calories);
            txt_ingredient_count = itemView.findViewById(R.id.txt_ingredient_count);
            label_group = itemView.findViewById(R.id.label_group);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onItemClick(getAdapterPosition());
        }
    }

    private class ViewHolderLoading extends RecyclerView.ViewHolder {
//        public ProgressBar progressBar;

        public ViewHolderLoading(View view) {
            super(view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = mInflater.inflate(R.layout.food_item, parent, false);
            return new ViewHolderRow(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = mInflater.inflate(R.layout.progress_bar_item, parent, false);
            return new ViewHolderLoading(view);
        }
        return null;


//        View itemView = mInflater.inflate(R.layout.food_item, parent, false);
//        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderRow) {
            ViewHolderRow holderRow = (ViewHolderRow) holder;
            if (hitsList != null) {
                Recipe recipe = getRecipe(position);
                holderRow.txt_recipe_title.setText(recipe.getLabel());
                Glide.with(activity)
                        .load(recipe.getImage())
                        .centerCrop()
                        .into(holderRow.img_food);
                holderRow.txt_calories.setText(Math.round(recipe.getCalories()) + " kcal");
                holderRow.txt_ingredient_count.setText(recipe.getIngredientLines().size() + " ingredients");

                List<String> labels = new ArrayList<>();
                labels.addAll(recipe.getDietLabels());
                labels.addAll(recipe.getHealthLabels());

                if (holderRow.label_group.getChildCount() == 0) {
                    for (int i = 0; i < labels.size(); i++) {
                        TextView label_view = (TextView) mInflater.inflate(R.layout.tag_item, null);
                        label_view.setText(labels.get(i));
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(convertToPixel(0), convertToPixel(4), convertToPixel(4), convertToPixel(4));
                        label_view.setLayoutParams(params);
                        holderRow.label_group.addView(label_view);
                    }
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (!hitsList.isEmpty()) {
            return hitsList.size() + 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position >= hitsList.size()) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    private int convertToPixel(int value) {
        Resources r = activity.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value,
                r.getDisplayMetrics()
        );
        return px;
    }
}
