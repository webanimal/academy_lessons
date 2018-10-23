package ru.webanimal.academy_lessons.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.ui.adapters.DigestAdapter;
import ru.webanimal.academy_lessons.utils.Application;
import ru.webanimal.academy_lessons.utils.DisplayUtils;

public class DigestListActivity extends AppCompatActivity {

    //==============================================================================================
    // Static
    //==============================================================================================

    private final static int GRID_LAYOUT_COLUMNS = 2;


    //==============================================================================================
    // Widgets
    //==============================================================================================

    private RecyclerView contentRecycler;
    private DigestAdapter adapter;


    //==============================================================================================
    // Activity callbacks
    //==============================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digests_list);
        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle(getString(R.string.activity_digests_list_title));

        onCreateDigests();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int actionId = item.getItemId();
        if (actionId == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return false;
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void onCreateDigests() {
        adapter = new DigestAdapter(Application.getApp().getRepository().getDigests());
        contentRecycler = findViewById(R.id.contentRecycler);
        contentRecycler.setLayoutManager(getLayoutManager());
        contentRecycler.setAdapter(adapter);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return DisplayUtils.isPortrait(this)
                ? new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                : new GridLayoutManager(this, GRID_LAYOUT_COLUMNS);
    }
}
