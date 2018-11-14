package ru.webanimal.academy_lessons.ui;

import ru.webanimal.academy_lessons.ui.digests.DigestsPresenter;
import ru.webanimal.academy_lessons.ui.digests.IDigestsPresenter;

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

    public IDigestsPresenter digests() {
        return digestsPresenter;
    }
}
