package ru.webanimal.academy_lessons.business;

import ru.webanimal.academy_lessons.business.digests.DigestsInteractorImpl;
import ru.webanimal.academy_lessons.business.digests.IDigestsInteractor;

public class InteractorManager {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static InteractorManager instance;
    public static InteractorManager get() {
        if (instance == null) {
            instance = new InteractorManager();
        }

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final IDigestsInteractor digestsInteractor;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private InteractorManager() {
        this.digestsInteractor = new DigestsInteractorImpl();
    }


    //==============================================================================================
    // Getters and Setters
    //==============================================================================================

    public IDigestsInteractor digestsInteractor() {
        return digestsInteractor;
    }
}
