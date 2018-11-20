package ru.webanimal.academy_lessons.data.features.digests.network;

import android.support.annotation.NonNull;

/**
 * To implement in the {@link ru.webanimal.academy_lessons.data.common.network.NetworkManager}
 */
public interface IDigestsRestApi {

    @NonNull
    IDigestsEndPoint digestsRestApi();
}
