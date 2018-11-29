package ru.webanimal.academy_lessons.utils;

import android.view.MenuItem;

public class DrawableUtils {

    public static void setMenuIconTintColor(MenuItem item, int colorId) {
        if (item != null) {
            item.getIcon().mutate().setTint(colorId);
        }
    }
}
