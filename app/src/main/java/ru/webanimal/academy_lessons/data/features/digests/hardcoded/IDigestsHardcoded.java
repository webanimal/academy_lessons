package ru.webanimal.academy_lessons.data.features.digests.hardcoded;

import android.support.annotation.NonNull;

public interface IDigestsHardcoded {

    @NonNull
    int getDefaultCategoryId();

    @NonNull
    int findCategoryIdForName(String name);

    @NonNull
    String getDefaultCategoryName();

    @NonNull
    String findCategoryNameForId(int id);
}
