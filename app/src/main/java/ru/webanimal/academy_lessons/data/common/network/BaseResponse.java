package ru.webanimal.academy_lessons.data.common.network;

import android.support.annotation.Nullable;

public class BaseResponse<T> {

    private final T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    @Nullable
    public T getData() {
        return data;
    }
}
