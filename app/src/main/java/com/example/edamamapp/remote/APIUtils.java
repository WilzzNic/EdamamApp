package com.example.edamamapp.remote;

public class APIUtils {
    static final String BASE_URL = "https://api.edamam.com/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
