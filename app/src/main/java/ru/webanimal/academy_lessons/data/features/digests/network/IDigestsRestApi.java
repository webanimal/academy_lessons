package ru.webanimal.academy_lessons.data.features.digests.network;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.webanimal.academy_lessons.data.common.network.BaseResponse;
import ru.webanimal.academy_lessons.data.common.network.modelsDTO.ResultsDTO;

public interface IDigestsRestApi {

    /**
     * Makes a http GET call.
     * Please select a category
     *
     * @param category a String to indicate what kind of getDigestsInteractor we have to download.
     *                 See {@link Categories#CATEGORIES}
     * @return a ReactiveX Observable with List<DigestDTO>
     */
    @NonNull
    @GET("{details}.json")
    Single<BaseResponse<ResultsDTO>> call(@Path("details") String category);
}
