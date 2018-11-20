package ru.webanimal.academy_lessons.data.common.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsDTO {

    @SerializedName("results")
    DigestDTO[] digests;

    public DigestDTO[] getDigests() {
        return digests;
    }
}
