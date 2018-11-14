package ru.webanimal.academy_lessons.data.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public final class ApiKeyInterceptor implements Interceptor {

    private ApiKeyInterceptor() {
    }

    public static ApiKeyInterceptor create() {

        return null;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
