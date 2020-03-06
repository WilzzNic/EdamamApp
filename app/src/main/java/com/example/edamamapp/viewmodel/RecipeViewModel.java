package com.example.edamamapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.repository.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository mRepository;
    private LiveData<SearchResponse> mResponse;

    public RecipeViewModel(@NonNull Application application) {
        super(application);

        mRepository = new RecipeRepository();
        mResponse = mRepository.getSearchResults();
    }

    public LiveData<SearchResponse> getSearchResults() {
        return mResponse;
    }
}
