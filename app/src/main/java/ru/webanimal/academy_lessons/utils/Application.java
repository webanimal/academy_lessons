package ru.webanimal.academy_lessons.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import ru.webanimal.academy_lessons.business.Interactors;

public class Application extends MultiDexApplication {

    private static Application sInstance;
    private Interactors interactors;

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

    public Interactors getInteractors() {
        return interactors;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        interactors = new Interactors();
    }
}
