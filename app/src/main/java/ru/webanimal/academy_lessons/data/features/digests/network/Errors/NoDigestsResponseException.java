package ru.webanimal.academy_lessons.data.features.digests.network.Errors;

/**
 * Throw this if no data have arrived
 */
public class NoDigestsResponseException extends Throwable {

    public NoDigestsResponseException() {
        super("Empty response exception");
    }
}
