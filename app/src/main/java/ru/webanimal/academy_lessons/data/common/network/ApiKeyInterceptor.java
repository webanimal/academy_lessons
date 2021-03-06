package ru.webanimal.academy_lessons.data.common.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    //==============================================================================================
    // Static
    //==============================================================================================

    public static Interceptor create(@NonNull String paramApiKey, @NonNull String apiKey) {
        return new ApiKeyInterceptor(paramApiKey, apiKey);
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final String paramApiKey;
    private final String apiKey;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private ApiKeyInterceptor(String paramApiKey, String apiKey) {
        this.paramApiKey = paramApiKey;
        this.apiKey = apiKey;
    }


    //==============================================================================================
    // Interceptor callbacks
    //==============================================================================================

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final Request request = chain.request();

        final HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(paramApiKey, apiKey)
                .build();

        final Request requestWithApiKey = request
                .newBuilder()
                .url(url)
                .build();

        return chain.proceed(requestWithApiKey);
    }
}
