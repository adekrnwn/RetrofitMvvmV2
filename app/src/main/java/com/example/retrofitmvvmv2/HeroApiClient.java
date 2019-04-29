package com.example.retrofitmvvmv2;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class HeroApiClient {

    private static final String BASE_URL = "https://simplifiedcoding.net/demos/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit==null){
             Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .callTimeout(15,TimeUnit.SECONDS)
                    .cache(null);

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return retrofit;
    }
}
