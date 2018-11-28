package ru.webanimal.academy_lessons.data.common.network.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MultimediaDTO {

    @SerializedName("url")
    @Expose
    private final String url;

    @SerializedName("caption")
    @Expose
    private final String caption;

    public MultimediaDTO(String url, String caption) {
        this.url = url;
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public String getCaption() {
        return caption;
    }
}
