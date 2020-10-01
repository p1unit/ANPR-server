package com.anpr.server.service;

import com.anpr.server.resorces.Resources;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAPIService {

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(Resources.VISION_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    private static HttpLoggingInterceptor logging
            = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC);

    public static <S> S createService(Class<S> serviceClass) {
        httpClient.interceptors().clear();
        httpClient.addInterceptor( chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Ocp-Apim-Subscription-Key",Resources.VISION_KEY)
                    .build();
            return chain.proceed(request);
        });
        httpClient.addInterceptor(logging);
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

}