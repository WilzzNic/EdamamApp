package com.example.edamamapp.remote;

import com.example.edamamapp.model.SearchResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APIService {
    @GET("search")
    Observable<SearchResponse> search(@QueryMap Map<String, String> params);

}
