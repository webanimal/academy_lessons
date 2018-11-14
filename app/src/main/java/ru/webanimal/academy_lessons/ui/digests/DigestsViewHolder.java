package ru.webanimal.academy_lessons.ui.digests;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.webanimal.academy_lessons.R;

public class DigestsViewHolder extends RecyclerView.ViewHolder {

    final TextView digestCategory;
    final TextView digestTitle;
    final TextView digestText;
    final TextView digestDate;
    final ImageView digestImage;

    DigestsViewHolder(View itemView) {
        super(itemView);

        digestCategory = itemView.findViewById(R.id.digestCategory);
        digestTitle = itemView.findViewById(R.id.digestTitle);
        digestText = itemView.findViewById(R.id.digestText);
        digestDate = itemView.findViewById(R.id.digestDate);
        digestImage = itemView.findViewById(R.id.digestImage);
    }
}
