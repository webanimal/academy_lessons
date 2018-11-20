package ru.webanimal.academy_lessons.data.common.network.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DigestDTO {

    @SerializedName("subsection")
    String category;

    @SerializedName("title")
    String title;

    @SerializedName("abstract")
    String shortText;

    // TODO (Sergio): Is a full text available??
    @SerializedName("abstract")
    String fullText;

    @SerializedName("published_date")
    String date;

    @SerializedName("multimedia")
    List<MultimediaDTO> multimediaDTO;

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getShortText() {
        return shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public String getDate() {
        return date;
    }

    public List<MultimediaDTO> getMultimediaDTO() {
        return multimediaDTO;
    }
}
