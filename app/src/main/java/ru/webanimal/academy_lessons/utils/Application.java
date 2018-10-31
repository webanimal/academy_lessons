package ru.webanimal.academy_lessons.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import ru.webanimal.academy_lessons.business.Interactors;
import ru.webanimal.academy_lessons.ui.BaseController;

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

    public static Application provides() {
        return sInstance;
    }

    public Context context() {
        return provides();
    }

    public Interactors interactors() {
        return Interactors.get();
    }

    public BaseController controller() {
        return BaseController.get();
    }
}
