package ru.webanimal.academy_lessons.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import ru.webanimal.academy_lessons.business.InteractorManager;
import ru.webanimal.academy_lessons.ui.PresenterManager;

public class Application extends MultiDexApplication {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private static Application sInstance;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    public Application() {
        super();
        sInstance = this;
    }


    //==============================================================================================
    // Getters and Setters
    //==============================================================================================

    public Context context() {
        return provides();
    }

    public static Application provides() {
        return sInstance;
    }

    public InteractorManager interactors() {
        return InteractorManager.get();
    }

    public PresenterManager presenters() {
        return PresenterManager.get();
    }
}
