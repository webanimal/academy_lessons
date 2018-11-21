package ru.webanimal.academy_lessons.data.common.network.modelsDTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DigestDTO {

    /**
     * 0 - Standard Thumbnail
     * 1 - thumbLarge
     * 2 - Normal
     * 3 - mediumThreeByTwo210
     * 4 - superJumbo
     */
    public final int DEFAULT_MULTIMEDIA_DTO_FORMAT = 2;

    @SerializedName("subsection")
    @Expose
    private String category;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("abstract")
    @Expose
    private String shortText;

    @SerializedName("published_date")
    @Expose
    private String date;

    @SerializedName("multimedia")
    @Expose
    private List<MultimediaDTO> multimediaDTO;

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

    public List<MultimediaDTO> getMultimediaDTO() {
        return multimediaDTO;
    }

    @Override
    public String toString() {
        return "category:" + category
                + ", title:" + title
                + ", shortText:" + shortText
                + ", date:" + date
                + ", image desc:" + multimediaDTO.get(DEFAULT_MULTIMEDIA_DTO_FORMAT).description
                + ", image url:" + multimediaDTO.get(DEFAULT_MULTIMEDIA_DTO_FORMAT).url;
    }
}
