package com.example.edamamapp.remote;

import com.example.edamamapp.model.SearchResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {
    @GET("search")
    Observable<SearchResponse> search(@Query("q") String q,
                                      @Query("app_id") String app_id,
                                      @Query("app_key") String app_key);

}
