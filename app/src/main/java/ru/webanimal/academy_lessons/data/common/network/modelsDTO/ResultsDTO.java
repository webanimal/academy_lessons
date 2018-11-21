package ru.webanimal.academy_lessons.data.common.network.modelsDTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsDTO {

    @SerializedName("results")
    @Expose
    List<DigestDTO> digests;

    public List<DigestDTO> getDigests() {
        return digests;
    }
}
