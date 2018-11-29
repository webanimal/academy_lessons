package ru.webanimal.academy_lessons.data.features.digests.hardcoded;

import android.support.annotation.NonNull;

public class DigestsHardcodedImpl implements IDigestsHardcoded {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static final int DEFAULT_CATEGORY = 15; // theater
    private static final String[] CATEGORIES = new String[] {
            "home", // 0
            "opinion",
            "world",
            "national",
            "politics",
            "upshot", // 5
            "nyregion",
            "business",
            "technology",
            "science",
            "health", // 10
            "sports",
            "arts",
            "books",
            "movies",
            "theater", // 15
            "sundayreview",
            "fashion",
            "tmagazine",
            "food",
            "travel", // 20
            "magazine",
            "realestate",
            "automobiles",
            "obituaries",
            "insider" // 25
    };


    //==============================================================================================
    // IDigestsHardcoded callbacks
    //==============================================================================================

    @Override
    public int getDefaultCategoryId() {
        return DEFAULT_CATEGORY;
    }

    @Override
    public int getCategoryIdForName(String name) {
        return idFor(name);
    }

    @NonNull
    @Override
    public String getDefaultCategoryName() {
        return CATEGORIES[DEFAULT_CATEGORY];
    }

    @NonNull
    @Override
    public String getCategoryNameForId(int id) {
        return at(id);
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private int idFor(String proposedCategory) {
        for (int i = 0; i < CATEGORIES.length; i++) {
            if (CATEGORIES[i].equalsIgnoreCase(proposedCategory)) {
                return i;
            }
        }
        return 0;
    }

    private String at(int position) {
        int length = CATEGORIES.length;
        int pos = position;
        if (pos < 0) {
            pos = 0;
        }
        if (pos >= length) {
            pos = length - 1;
        }

        return CATEGORIES[pos];
    }
}
