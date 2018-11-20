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

    @SerializedName("url")
    String url;

    @SerializedName("multimedia")
    List<MultimediaDTO> multimediaDTO;
}
