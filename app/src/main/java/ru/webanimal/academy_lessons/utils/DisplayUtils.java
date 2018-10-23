package ru.webanimal.academy_lessons.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Surface;

public class DisplayUtils {

    public static int getDisplayRotation(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getRotation();
    }

    public static boolean isPortrait(Activity activity) {
        int rotation = getDisplayRotation(activity);
        return rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180;
    }

    public static int pxToDp(Context context, int px) {
        return px * 160 / context.getResources().getDisplayMetrics().densityDpi;
    }

    public static int dpToPx(Context context, int dp) {
        return dp * context.getResources().getDisplayMetrics().densityDpi / 160;
    }
}
