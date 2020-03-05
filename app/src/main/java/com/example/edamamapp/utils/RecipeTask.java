package com.example.edamamapp.utils;

import com.example.edamamapp.BuildConfig;
import com.example.edamamapp.callback.Callback;
import com.example.edamamapp.model.SearchResponse;
import com.example.edamamapp.remote.APIUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RecipeTask {
    public static void searchRecipe(final Callback<SearchResponse> callback) {
        APIUtils.getAPIService()
                .search("chicken", BuildConfig.EDAMAM_ID, BuildConfig.EDAMAM_KEY)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SearchResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        System.out.println("Subscribed.");
                    }

                    @Override
                    public void onNext(@NonNull SearchResponse searchResponse) {
                        System.out.println(searchResponse);
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
    }
}
