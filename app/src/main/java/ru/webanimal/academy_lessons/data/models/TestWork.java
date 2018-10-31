package ru.webanimal.academy_lessons.data.models;

import android.util.Log;

public class TestWork {

    public static String doWork() {
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(500);
                Log.d("tag", "do work #" + i);
            }
            return "Thread COMPLETED";

        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Thread ERROR";
        }
    }
}