package ru.webanimal.academy_lessons.data.features.digests.network.errors;

/**
 * Throws this if no data have arrived
 */
public class NoDigestsResponseException extends Throwable {

    public NoDigestsResponseException() {
        super("Empty response exception");
    }
}
