package ru.webanimal.academy_lessons.data.models;

import java.io.Serializable;
import java.util.Date;

import ru.webanimal.academy_lessons.utils.DateTimeUtils;

public class DigestItem implements Serializable {

    private final transient Category category;
    private final transient Date publishDate;

    private final String title;
    private final String previewText;
    private final String fullText;
    private final String imageUrl;
    private final String categoryName;
    private final String dateName;

    public DigestItem(String title, String imageUrl, Category category, Date publishDate, String previewText, String fullText) {
        this.category = category;
        this.title = title;
        this.previewText = previewText;
        this.fullText = fullText;
        this.publishDate = publishDate;
        this.imageUrl = imageUrl;

        this.categoryName = this.category.getName();
        this.dateName = DateTimeUtils.getPublishedDateAsFormattedString(this.publishDate);
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getPreviewText() {
        return previewText;
    }

    public String getFullText() {
        return fullText;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDateName() {
        return dateName;
    }
}