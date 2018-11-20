package ru.webanimal.academy_lessons.ui.common;

import android.support.annotation.NonNull;

import ru.webanimal.academy_lessons.ui.features.digests.DigestsPresenter;
import ru.webanimal.academy_lessons.ui.features.digests.IDigestsPresenter;

public class PresenterManager {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static PresenterManager instance;
    public static PresenterManager get() {
        if (instance == null) {
            instance = new PresenterManager();
        }

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final DigestsPresenter digestsPresenter;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private PresenterManager() {
        this.digestsPresenter = new DigestsPresenter();
    }


    //==============================================================================================
    // Getters and Setters
    //==============================================================================================

    @NonNull
    public IDigestsPresenter digests() {
        return digestsPresenter;
    }
}
