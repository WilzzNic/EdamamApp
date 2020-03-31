package com.example.edamamapp.remote;

import com.example.edamamapp.BuildConfig;

import java.util.HashMap;
import java.util.Map;

public class APIUtils {
    static final String BASE_URL = "https://api.edamam.com/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

    public static Map<String, String> getBaseQuery(String keyword) {
        Map<String, String> query = new HashMap<>();
        query.put("q", keyword);
        query.put("app_id", BuildConfig.EDAMAM_ID);
        query.put("app_key", BuildConfig.EDAMAM_KEY);
        return query;
    }
}
