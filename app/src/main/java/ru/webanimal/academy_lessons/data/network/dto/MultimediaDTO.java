package ru.webanimal.academy_lessons.data.network.dto;

import com.google.gson.annotations.SerializedName;

public class MultimediaDTO {

    @SerializedName("url")
    String url;

    @SerializedName("caption")
    String description;
}
