package com.example.edamamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.utils.RecipeListAdapter;
import com.example.edamamapp.viewmodel.RecipeViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    private RecipeViewModel mRecipeViewModel;
    private RecyclerView recyclerView;
    private ChipGroup diet_chips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diet_chips = findViewById(R.id.diet_chips);

        recyclerView = findViewById(R.id.recycler_view);
        RecipeListAdapter adapter = new RecipeListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mRecipeViewModel.getSearchResults().observe(this, new Observer<SearchResponse>() {
            @Override
            public void onChanged(SearchResponse searchResponse) {
                adapter.setRecipes(searchResponse.getHitsList());
            }
        });

        String[] dietFilterArray = getResources().getStringArray(R.array.diet_type);

        for (int i = 0; i < dietFilterArray.length; i++) {
            Chip chip = new Chip(this);
            chip.setText(dietFilterArray[i]);
            ChipDrawable drawable = ChipDrawable.createFromAttributes(this,
                    null, 0, R.style.Widget_MaterialComponents_Chip_Choice);
            chip.setChipDrawable(drawable);
            chip.setTextAppearance(R.style.ChipText);
            //chip.setCloseIconEnabled(true);
            //chip.setCloseIconResource(R.drawable.your_icon);
            //chip.setChipIconResource(R.drawable.your_icon);
            //chip.setChipBackgroundColorResource(R.color.red);
            //chip.setTextAppearanceResource(R.style.ChipTextStyle);
            //chip.setElevation(15);

            diet_chips.addView(chip);
        }

        diet_chips.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                if (checkedId != -1) {
//                    System.out.println(dietFilterArray[checkedId-1].toLowerCase());
                    mRecipeViewModel.setDiet(dietFilterArray[checkedId-1].toLowerCase());
                }
                else {
                    mRecipeViewModel.setDiet(null);
                }
            }
        });
    }
}
