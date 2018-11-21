package ru.webanimal.academy_lessons.data.common.network.modelsDTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultimediaDTO {

    @SerializedName("url")
    @Expose
    String url;

    @SerializedName("caption")
    @Expose
    String description;

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }
}
