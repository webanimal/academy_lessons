package ru.webanimal.academy_lessons.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import ru.webanimal.academy_lessons.data.DigestRepositoryImpl;

public class Application extends MultiDexApplication {

    private static Application sInstance;
    private DigestRepositoryImpl repository;

    public Application() {
        super();
        sInstance = this;
    }

    public static Application getApp() {
        return sInstance;
    }

    public static Context getContext() {
        return getApp();
    }

    public DigestRepositoryImpl getRepository() {
        return repository;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        repository = new DigestRepositoryImpl();
    }
}
