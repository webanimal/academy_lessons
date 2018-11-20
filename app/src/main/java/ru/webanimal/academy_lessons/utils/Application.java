package ru.webanimal.academy_lessons.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import ru.webanimal.academy_lessons.business.common.InteractorManager;
import ru.webanimal.academy_lessons.data.common.network.NetworkManager;
import ru.webanimal.academy_lessons.ui.common.PresenterManager;

public class Application extends MultiDexApplication {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static Application sInstance;

    @NonNull
    public static Application provides() {
        return sInstance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final PresenterManager presenterManager;
    private final InteractorManager interactorManager;
    private final NetworkManager networkManager;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    public Application() {
        super();
        sInstance = this;

        // final fields
        presenterManager = PresenterManager.get();
        interactorManager = InteractorManager.get();
        networkManager = NetworkManager.get();
    }


    //==============================================================================================
    // Getters and Setters
    //==============================================================================================

    @NonNull
    public Context context() {
        return provides();
    }

    @NonNull
    public PresenterManager presenters() {
        return presenterManager;
    }

    @NonNull
    public InteractorManager interactors() {
        return interactorManager;
    }

    @NonNull
    public NetworkManager network() {
        return networkManager;
    }
}
