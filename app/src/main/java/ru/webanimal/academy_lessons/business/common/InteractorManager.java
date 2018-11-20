package ru.webanimal.academy_lessons.business.common;

import ru.webanimal.academy_lessons.business.features.digests.DigestsFacadeImpl;
import ru.webanimal.academy_lessons.business.features.digests.IDigestsFacade;

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

    private final IDigestsFacade digestsFacade;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private InteractorManager() {
        this.digestsFacade = new DigestsFacadeImpl();
    }


    //==============================================================================================
    // Getters and Setters
    //==============================================================================================

    public IDigestsFacade digests() {
        return digestsFacade;
    }
}