package ru.webanimal.academy_lessons.data.network;

import android.support.annotation.Nullable;

public class BaseResponse<T> {

    private T data;

    @Nullable
    public T getData() {
        return data;
    }
}
