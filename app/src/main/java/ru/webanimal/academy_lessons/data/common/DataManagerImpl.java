package ru.webanimal.academy_lessons.data.common;

import android.support.annotation.NonNull;

import ru.webanimal.academy_lessons.data.common.hardcoded.HardcoreManager;
import ru.webanimal.academy_lessons.data.common.network.NetworkManager;

public class DataManagerImpl implements IDataManager {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static DataManagerImpl instance;
    public static DataManagerImpl get() {
        if (instance == null) {
            instance = new DataManagerImpl();
        }
        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final NetworkManager networkManager;
    private final HardcoreManager hardcoreManager;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private DataManagerImpl() {
        networkManager = NetworkManager.get();
        hardcoreManager = HardcoreManager.get();
    }


    //==============================================================================================
    // IDataManager callbacks
    //==============================================================================================

    @NonNull
    @Override
    public NetworkManager fromNetwork() {
        return networkManager;
    }

    @NonNull
    @Override
    public HardcoreManager fromHardcore() {
        return hardcoreManager;
    }
}
