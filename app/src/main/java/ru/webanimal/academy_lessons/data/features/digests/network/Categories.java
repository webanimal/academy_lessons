package ru.webanimal.academy_lessons.data.features.digests.network;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Categories {

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
        List<String> categories = Arrays.asList(CATEGORIES);
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).equalsIgnoreCase(proposedCategory)) {
                return i;
            }
        }
        return -1;
    }
}
