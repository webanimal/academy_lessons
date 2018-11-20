package ru.webanimal.academy_lessons.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import ru.webanimal.academy_lessons.business.common.InteractorManager;
import ru.webanimal.academy_lessons.data.common.DataManagerImpl;
import ru.webanimal.academy_lessons.data.common.IDataManager;
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
    private final DataManagerImpl dataManager;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    public Application() {
        super();
        sInstance = this;

        // final fields
        presenterManager = PresenterManager.get();
        interactorManager = InteractorManager.get();
        dataManager = DataManagerImpl.get();
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
    public IDataManager dataFrom() {
        return dataManager;
    }
}
