package ru.webanimal.academy_lessons.business.common;

/**
 * Throws this on any exception
 */
public class UnknownException extends Throwable {

    public UnknownException() {
        super("Unknown exception");
    }
}
