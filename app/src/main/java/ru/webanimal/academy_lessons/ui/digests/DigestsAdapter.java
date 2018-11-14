package ru.webanimal.academy_lessons.ui.digests;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.ui.digest.DigestActivity;
import ru.webanimal.academy_lessons.utils.DateTimeUtils;

public class DigestsAdapter extends RecyclerView.Adapter<DigestsViewHolder> {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static final int LAYOUT_DIGEST_ITEM =  R.layout.layout_digest;


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final List<DigestItem> digests = new ArrayList<>();


    //==============================================================================================
    // RecyclerView.Adapter callbacks
    //==============================================================================================

    @NonNull
    @Override
    public DigestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DigestsViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(LAYOUT_DIGEST_ITEM, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DigestsViewHolder holder, int position) {
        bindHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return digests.size();
    }


    //==============================================================================================
    // Public methods
    //==============================================================================================
    public void setData(@NonNull List<DigestItem> dataSet) {
        digests.clear();
        digests.addAll(dataSet);
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void bindHolder(DigestsViewHolder holder, int position) {
        DigestItem item = digests.get(position);
        holder.digestCategory.setText(item.getCategory().getName());
        holder.digestTitle.setText(item.getTitle());
        holder.digestText.setText(item.getPreviewText());
        holder.digestDate.setText(DateTimeUtils.getPublishedDateAsFormattedString(item.getPublishDate()));

        loadImage(holder.digestImage, item.getImageUrl());

        holder.itemView.setOnClickListener(
                getItemCount() > 1 ? new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DigestActivity.startDigestActivity(v.getContext(), item);
                    }
                } : null);
    }

    private void loadImage(ImageView view, String url) {
        if (view != null && !TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(url)
                    .apply(new RequestOptions().fitCenter().placeholder(R.drawable.ic_img_loading))
                    .into(view);
        }
    }
}
