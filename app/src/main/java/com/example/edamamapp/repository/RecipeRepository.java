package com.example.edamamapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.edamamapp.BuildConfig;
import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.remote.APIUtils;

import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeRepository {

    public LiveData<SearchResponse> getSearchResults(String query, String diet, Integer from, Integer to) {
        MutableLiveData<SearchResponse> searchData = new MutableLiveData<>();

        Map<String, String> params = APIUtils.getBaseQuery(query);
        params.put("from", String.valueOf(from));
        params.put("to", String.valueOf(to));

        if (diet != null) {
            params.put("diet", diet);
        }

        APIUtils.getAPIService().search(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull SearchResponse searchResponse) {
                        searchData.setValue(searchResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed");
                    }
                });
        return searchData;
    }
}
