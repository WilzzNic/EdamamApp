package com.example.edamamapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.repository.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository mRepository;
    public MutableLiveData<String> diet = new MutableLiveData<>();
    private LiveData<SearchResponse> mResponse;
//    private Boolean mRetry = false;

    public RecipeViewModel(@NonNull Application application) {
        super(application);

        mRepository = new RecipeRepository();
        diet.setValue(null);
        mResponse = Transformations.switchMap(diet, d ->
                mRepository.getSearchResults(d)
        );
    }

    public LiveData<SearchResponse> getSearchResults() {
        return mResponse;
    }

    public void setDiet(String diet) {
        this.diet.setValue(diet);
    }
}
