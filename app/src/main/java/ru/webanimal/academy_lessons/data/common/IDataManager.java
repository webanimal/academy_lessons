package ru.webanimal.academy_lessons.data.common;

import android.support.annotation.NonNull;

import ru.webanimal.academy_lessons.data.common.network.NetworkManager;

public interface IDataManager {

    @NonNull
    NetworkManager network();
}
