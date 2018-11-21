package ru.webanimal.academy_lessons.data.common.network.modelsDTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsDTO {

    @SerializedName("results")
    @Expose
    private List<DigestDTO> results = null;

    public List<DigestDTO> getResults() {
        return this.results;
    }

    public void setResults(List<DigestDTO> results) {
        this.results = results;
    }
}
