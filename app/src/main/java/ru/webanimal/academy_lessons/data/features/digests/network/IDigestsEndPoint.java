package ru.webanimal.academy_lessons.data.features.digests.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.webanimal.academy_lessons.data.common.network.dto.DigestDTO;

public interface IDigestsEndPoint {

    /**
     * Makes a http GET call.
     * Please select a category
     *
     * @param category
     * @return
     */
    @NonNull
    @GET("{details}.json")
    Single<List<DigestDTO>> call(@Path("details") String category);
}
