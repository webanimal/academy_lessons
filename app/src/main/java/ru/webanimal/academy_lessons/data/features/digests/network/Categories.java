package ru.webanimal.academy_lessons.data.features.digests.network;

import java.util.Arrays;
import java.util.List;

public class Categories {

//    public static final int DEFAULT_CATEGORY = 23; // I.e. automobiles
    public static final int DEFAULT_CATEGORY = 5; // I.e. theater

    private static final String[] CATEGORIES = new String[] {
            "home",
            "opinion",
            "world",
            "national",
            "politics",
            "upshot",
            "nyregion",
            "business",
            "technology",
            "science",
            "health",
            "sports",
            "arts",
            "books",
            "movies",
            "theater",
            "sundayreview",
            "fashion",
            "tmagazine",
            "food",
            "travel",
            "magazine",
            "realestate",
            "automobiles",
            "obituaries",
            "insider"
    };

    public static String at(int position) {
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

    public static int idFor(String proposedCategory) {
        for (int i = 0; i < CATEGORIES.length; i++) {
            if (CATEGORIES[i].equalsIgnoreCase(proposedCategory)) {
                return i;
            }
        }
        return -1;
    }
}
