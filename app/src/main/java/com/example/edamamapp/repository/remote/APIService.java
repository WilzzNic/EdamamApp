package com.example.edamamapp.repository.remote;

import com.example.edamamapp.repository.model.SearchResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface APIService {
    @Headers("Accept-Encoding: gzip")
    @GET("search")
    Observable<SearchResponse> search();

}
