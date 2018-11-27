package ru.webanimal.academy_lessons.data.features.digests.network.Errors;

/**
 * Throw this if on bad response or some internet errors
 */
public class BadDigestsResponseException extends Throwable {

    public BadDigestsResponseException() {
        super("Bad response exception");
    }
}
