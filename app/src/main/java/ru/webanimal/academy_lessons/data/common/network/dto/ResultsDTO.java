package ru.webanimal.academy_lessons.data.common.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsDTO {

    @SerializedName("results")
    List<DigestDTO> digests;

    public List<DigestDTO> getDigests() {
        return digests;
    }
}
