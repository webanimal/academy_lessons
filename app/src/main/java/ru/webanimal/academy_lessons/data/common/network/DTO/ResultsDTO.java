package ru.webanimal.academy_lessons.data.common.network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsDTO {

    @SerializedName("status")
    @Expose
    private final String status;

    @SerializedName("results")
    @Expose
    private final List<DigestDTO> results;

    public ResultsDTO(String status, List<DigestDTO> results) {
        this.status = status;
        this.results = results;
    }

    public String getStatus() { return this.status; }

    public List<DigestDTO> getResults() {
        return this.results;
    }
}
