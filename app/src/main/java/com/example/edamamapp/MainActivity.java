package com.example.edamamapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.LayoutTransition;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;

import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.utils.EndlessRecyclerViewScrollListener;
import com.example.edamamapp.utils.RecipeListAdapter;
import com.example.edamamapp.viewmodel.RecipeViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {
    private RecipeViewModel mRecipeViewModel;
    private RecyclerView recyclerView;
    private ChipGroup diet_chips;
    private EndlessRecyclerViewScrollListener scrollListener;
    private String[] dietFilterArray;
    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTopToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);

        diet_chips = findViewById(R.id.diet_chips);

        recyclerView = findViewById(R.id.recycler_view);
        RecipeListAdapter adapter = new RecipeListAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        mRecipeViewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        mRecipeViewModel.getSearchResults().observe(this, new Observer<SearchResponse>() {
            @Override
            public void onChanged(SearchResponse searchResponse) {
                adapter.setRecipes(searchResponse.getHitsList());
//                adapter.setLoaded();
            }
        });

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
//                System.out.println("Page: " + page);
//                System.out.println("Loading...");
//                adapter.setLoading();
                mRecipeViewModel.nextPage();
            }
        };

        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        populateChips();

        diet_chips.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                adapter.resetList();
                scrollListener.resetState();

                if (checkedId != -1) {
                    mRecipeViewModel.setDiet(dietFilterArray[checkedId-1].toLowerCase());
                }
                else {
                    mRecipeViewModel.setDiet(null);
                }
            }
        });
    }

    public void populateChips() {
        dietFilterArray = getResources().getStringArray(R.array.diet_type);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_search_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        LinearLayout searchBar = searchView.findViewById(R.id.search_bar);
        searchBar.setLayoutTransition(new LayoutTransition());

        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default

        return true;
    }
}
