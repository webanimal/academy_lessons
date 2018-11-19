package ru.webanimal.academy_lessons.ui.digests;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ru.webanimal.academy_lessons.data.models.DigestItem;

public class DigestsAdapter extends RecyclerView.Adapter<DigestsViewHolder> {

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
        Log.d("tag", "onCreateViewHolder");
        return DigestsViewHolder.create(parent, viewType, Glide.with(parent));
    }

    @Override
    public void onBindViewHolder(@NonNull DigestsViewHolder holder, int position) {
        Log.d("tag", "onBindViewHolder position: " + position);
        holder.bindItem(getItem(position), isClickable());
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

    private DigestItem getItem(int position) {
        return digests.get(position);
    }

    // TODO (Sergio): remove after normal networking
    private boolean isClickable() {
        return getItemCount() > 1;
    }
}
