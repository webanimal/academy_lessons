package ru.webanimal.academy_lessons.data.common;

import android.support.annotation.NonNull;

import ru.webanimal.academy_lessons.data.common.network.NetworkManager;

public class DataManagerImpl implements IDataManager {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static DataManagerImpl instance;
    public static synchronized DataManagerImpl get() {
        if (instance == null) {
            instance = new DataManagerImpl();
        }
        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final NetworkManager networkManager;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private DataManagerImpl() {
        // final fields
        networkManager = NetworkManager.get();
    }


    //==============================================================================================
    // IDataManager callbacks
    //==============================================================================================

    @NonNull
    public NetworkManager fromNetwork() {
        return networkManager;
    }
}
