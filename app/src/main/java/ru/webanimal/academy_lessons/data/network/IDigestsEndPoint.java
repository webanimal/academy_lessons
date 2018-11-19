package ru.webanimal.academy_lessons.data.network;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.webanimal.academy_lessons.data.network.dto.DigestDTO;

public interface IDigestsEndPoint {

    @NonNull
    @GET("{details}.json")
    Call<List<DigestDTO>> digests(@Path("details") String category);
}
