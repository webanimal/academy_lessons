package ru.webanimal.academy_lessons.utils.containers;

public class TwoPiecesContainer<T> {

    private final T data;
    private final Throwable error;

    public TwoPiecesContainer(T data, Throwable error) {
        this.data = data;
        this.error = error;
    }

    public T getFirst() {
        return data;
    }

    public Throwable getSecond() {
        return error;
    }
}
