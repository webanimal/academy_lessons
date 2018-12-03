package ru.webanimal.academy_lessons.data.common.hardcoded;

import android.support.annotation.NonNull;

import ru.webanimal.academy_lessons.data.features.digests.hardcoded.DigestsHardcodedImpl;
import ru.webanimal.academy_lessons.data.features.digests.hardcoded.IDigestsHardcoded;
import ru.webanimal.academy_lessons.data.features.digests.hardcoded.IDigestsHardcodedManager;

public class HardcoreManager implements IDigestsHardcodedManager {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static HardcoreManager instance;
    public static synchronized HardcoreManager get() {
        if (instance == null) {
            instance = new HardcoreManager();
        }

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private IDigestsHardcoded digests;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private HardcoreManager() {
        //init hardcoded sources here. Can be more then one endpoint
        digests = new DigestsHardcodedImpl();
    }


    //==============================================================================================
    // Hardcoded managers callbacks
    //==============================================================================================

    @NonNull
    @Override
    public IDigestsHardcoded forDigests() {
        return digests;
    }
}
