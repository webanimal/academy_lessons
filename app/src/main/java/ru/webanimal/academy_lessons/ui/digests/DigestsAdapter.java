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
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.ui.digest.DigestActivity;
import ru.webanimal.academy_lessons.utils.DateTimeUtils;

public class DigestsAdapter extends RecyclerView.Adapter<DigestsAdapter.ViewHolder> {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private final List<DigestItem> digests = new ArrayList<>();


    //==============================================================================================
    // Constructor
    //==============================================================================================

    DigestsAdapter() {
    }


    //==============================================================================================
    // RecyclerView.Adapter callbacks
    //==============================================================================================

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_digest, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

    private void bindHolder(ViewHolder holder, int position) {
        DigestItem item = digests.get(position);
        holder.digestCategory.setText(item.getCategory().getName());
        holder.digestTitle.setText(item.getTitle());
        holder.digestText.setText(item.getPreviewText());
        holder.digestDate.setText(DateTimeUtils.getPublishedDateAsFormattedString(item.getPublishDate()));

        loadImage(holder.digestImage, item.getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DigestActivity.startDigestActivity(v.getContext(), item);
            }
        });
    }

    private void loadImage(ImageView view, String url) {
        if (view != null && !TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(url)
                    .apply(new RequestOptions().fitCenter().placeholder(R.drawable.ic_img_loading))
                    .into(view);
        }
    }


    //==============================================================================================
    // Inner classes
    //==============================================================================================

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView digestCategory;
        final TextView digestTitle;
        final TextView digestText;
        final TextView digestDate;
        final ImageView digestImage;

        ViewHolder(View itemView) {
            super(itemView);

            digestCategory = itemView.findViewById(R.id.digestCategory);
            digestTitle = itemView.findViewById(R.id.digestTitle);
            digestText = itemView.findViewById(R.id.digestText);
            digestDate = itemView.findViewById(R.id.digestDate);
            digestImage = itemView.findViewById(R.id.digestImage);
        }
    }
}
