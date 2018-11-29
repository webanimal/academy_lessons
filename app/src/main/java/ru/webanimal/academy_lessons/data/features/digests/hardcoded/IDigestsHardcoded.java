package ru.webanimal.academy_lessons.data.features.digests.hardcoded;

import android.support.annotation.NonNull;

public interface IDigestsHardcoded {

    int getDefaultCategoryId();

    int getCategoryIdForName(String name);

    @NonNull
    String getDefaultCategoryName();

    @NonNull
    String getCategoryNameForId(int id);

    @NonNull
    String[] getAllCategories();
}
