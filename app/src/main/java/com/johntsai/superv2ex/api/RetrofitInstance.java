package com.johntsai.superv2ex.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit;
    private static final Gson gson =
            new Gson().newBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();

    private RetrofitInstance() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.v2ex.com/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static class HOLDER {
        private static final RetrofitInstance INSTANCE = new RetrofitInstance();
    }

    public static RetrofitInstance get() {
        return HOLDER.INSTANCE;
    }

    public V2exService getService() {
        return retrofit.create(V2exService.class);
    }

}
