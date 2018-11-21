package ru.webanimal.academy_lessons.data.common.network.modelsDTO;

import com.google.gson.annotations.SerializedName;

public class ResultsDTO {

    @SerializedName("results")
    DigestDTO[] digests;

    public DigestDTO[] getDigests() {
        return digests;
    }
}
