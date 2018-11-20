package ru.webanimal.academy_lessons.utils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import ru.webanimal.academy_lessons.business.common.InteractorManager;
import ru.webanimal.academy_lessons.data.common.network.NetworkManager;
import ru.webanimal.academy_lessons.ui.common.PresenterManager;

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

    public PresenterManager presenters() {
        return PresenterManager.get();
    }

    public InteractorManager interactors() {
        return InteractorManager.get();
    }

    public NetworkManager network() {
        return NetworkManager.get();
    }
}
