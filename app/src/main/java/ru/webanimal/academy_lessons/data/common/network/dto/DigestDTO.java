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

    @SerializedName("published_date")
    String date;

    @SerializedName("multimedia")
    MultimediaDTO[] multimediaDTO;

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getShortText() {
        return shortText;
    }

    // Is there a full text available??
    public String getFullText() {
        return shortText;
    }

    public String getDate() {
        return date;
    }

    public MultimediaDTO[] getMultimediaDTO() {
        return multimediaDTO;
    }
}
