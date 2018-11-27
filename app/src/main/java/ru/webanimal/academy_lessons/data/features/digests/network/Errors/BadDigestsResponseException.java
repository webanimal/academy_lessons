package ru.webanimal.academy_lessons.data.features.digests.network.Errors;

/**
 * Throws this on bad response or on some internet errors
 */
public class BadDigestsResponseException extends Throwable {

    public BadDigestsResponseException() {
        super("Bad response exception");
    }
}
