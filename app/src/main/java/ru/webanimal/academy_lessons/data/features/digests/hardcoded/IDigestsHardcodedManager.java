package ru.webanimal.academy_lessons.data.features.digests.hardcoded;

import android.support.annotation.NonNull;

/**
 * To implement in the {@link ru.webanimal.academy_lessons.data.common.hardcoded.HardcoreManager}
 */
public interface IDigestsHardcodedManager {

    @NonNull
    IDigestsHardcoded forDigests();
}
