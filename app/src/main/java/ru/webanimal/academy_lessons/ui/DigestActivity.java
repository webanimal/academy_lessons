package ru.webanimal.academy_lessons.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public class DigestActivity extends AppCompatActivity {

    private static final String ARGS_DIGEST_ITEM = "ARGS_DIGEST_ITEM";

    private DigestItem digest;

    public static void startDigestActivity(Context ctx, DigestItem digest) {
        Bundle args = new Bundle();
        args.putSerializable(ARGS_DIGEST_ITEM, digest);
        ctx.startActivity(new Intent(ctx, DigestActivity.class).putExtras(args));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digest);
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setDigest();
        setTitle(hasDigest() ? digest.getCategoryName() : getString(R.string.activity_digest_title));

        setContent();
    }

    private void setDigest() {
        Bundle args = getIntent().getExtras();
        if (args != null) {
            digest = (DigestItem) args.getSerializable(ARGS_DIGEST_ITEM);
        }
    }

    private void setContent() {
        setAvatar();

        if (hasDigest()) {
            ((TextView)findViewById(R.id.digestTitle)).setText(digest.getTitle());
            ((TextView)findViewById(R.id.digestDate)).setText(digest.getDateName());
            ((TextView)findViewById(R.id.digestText)).setText(digest.getFullText());
        }
    }

    private void setAvatar() {
        if (hasDigest()) {
            Glide.with(this)
                    .load(digest.getImageUrl())
                    .apply(new RequestOptions().fitCenter())
                    .into((AppCompatImageView)findViewById(R.id.digestImage));
        }
    }

    private boolean hasDigest() {
        return digest != null;
    }
}
