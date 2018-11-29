package ru.webanimal.academy_lessons.data.features.digests.hardcoded;

import android.support.annotation.NonNull;

public interface IDigestsHardcoded {

    @NonNull
    int getDefaultCategoryId();

    @NonNull
    int getCategoryIdForName(String name);

    @NonNull
    String getDefaultCategoryName();

    @NonNull
    String getCategoryNameForId(int id);
}
