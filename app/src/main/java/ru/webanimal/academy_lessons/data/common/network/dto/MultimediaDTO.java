package ru.webanimal.academy_lessons.data.common.network.dto;

import com.google.gson.annotations.SerializedName;

public class MultimediaDTO {

    @SerializedName("url")
    String url;

    @SerializedName("caption")
    String description;
}