package ru.webanimal.academy_lessons.ui.digests;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.ui.digest.DigestActivity;
import ru.webanimal.academy_lessons.utils.DateTimeUtils;

public class DigestsViewHolder extends RecyclerView.ViewHolder {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static final int LAYOUT_DIGEST_ITEM =  R.layout.layout_digest;

    public static DigestsViewHolder create(@NonNull ViewGroup parent, int viewType, RequestManager glideManager) {
        return new DigestsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(LAYOUT_DIGEST_ITEM, parent,false),
                glideManager);
    }


    //==============================================================================================
    // Widgets
    //==============================================================================================

    private TextView digestCategory;
    private TextView digestTitle;
    private TextView digestText;
    private TextView digestDate;
    private ImageView digestImage;


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final RequestManager glideManager;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private DigestsViewHolder(View itemView, RequestManager glideManager) {
        super(itemView);
        findViews(itemView);
        this.glideManager = glideManager;
    }


    //==============================================================================================
    // Public methods
    //==============================================================================================

    public void bindItem(DigestItem item, boolean isSingleItemClickable) {
        digestCategory.setText(item.getCategory().getName());
        digestTitle.setText(item.getTitle());
        digestText.setText(item.getPreviewText());
        digestDate.setText(DateTimeUtils.getPublishedDateAsFormattedString(item.getPublishDate()));

        loadImage(digestImage, item.getImageUrl());

        itemView.setOnClickListener(isSingleItemClickable
                ? new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DigestActivity.startDigestActivity(v.getContext(), item);
                    }}
                : null);
    }

    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void findViews(View itemView) {
        digestCategory = itemView.findViewById(R.id.digestCategory);
        digestTitle = itemView.findViewById(R.id.digestTitle);
        digestText = itemView.findViewById(R.id.digestText);
        digestDate = itemView.findViewById(R.id.digestDate);
        digestImage = itemView.findViewById(R.id.digestImage);
    }

    private void loadImage(ImageView view, String url) {
        if (view != null && !TextUtils.isEmpty(url)) {
            glideManager
                    .load(url)
                    .apply(new RequestOptions()
                            .fitCenter()
                            .placeholder(R.drawable.ic_img_loading)
                            .error(R.drawable.ic_img_download_failed))
                    .into(view);
        }
    }
}
