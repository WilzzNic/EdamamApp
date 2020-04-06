package com.example.edamamapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.edamamapp.model.SearchParams;
import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.repository.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {
    private RecipeRepository mRepository;
    private SearchParams searchParams = new SearchParams();
    private MutableLiveData<String> diet = new MutableLiveData<>();
    private MutableLiveData<SearchParams> searchParamsLiveData = new MutableLiveData<>();
    private LiveData<SearchResponse> mResponse;
    private int pageFrom = 0;
    private int pageTo = 10;

    public RecipeViewModel(@NonNull Application application) {
        super(application);

        mRepository = new RecipeRepository();

        init();

        mResponse = Transformations.switchMap(searchParamsLiveData, x ->
                mRepository.getSearchResults(x.getDiet(), x.getFrom(), x.getTo()));

//        mResponse = Transformations.switchMap(diet, d ->
//                mRepository.getSearchResults(d, from, to)
//        );
    }

    private void init() {
        searchParams.setDiet(null);
        searchParams.setFrom(0);
        searchParams.setTo(10);
        searchParamsLiveData.setValue(searchParams);
    }

    public LiveData<SearchResponse> getSearchResults() {
        return mResponse;
    }

    public void nextPage() {
        searchParams.setFrom(pageFrom += 10);
        searchParams.setTo(pageTo += 10);
        searchParamsLiveData.setValue(searchParams);
    }

    public void setDiet(String diet) {
        searchParams.setDiet(diet);
        searchParamsLiveData.setValue(searchParams);
    }
}
