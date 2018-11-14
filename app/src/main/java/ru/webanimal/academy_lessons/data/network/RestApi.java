package ru.webanimal.academy_lessons.data.network;

public final class RestApi {

    private static RestApi instance;

    private RestApi() {
    }

    public static synchronized RestApi getInstance() {
        if (instance == null) instance = new RestApi();

        return instance;
    }


}
