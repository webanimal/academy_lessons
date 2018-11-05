package ru.webanimal.academy_lessons.ui.digestsList;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.ui.BaseActivity;
import ru.webanimal.academy_lessons.ui.about.AboutActivity;
import ru.webanimal.academy_lessons.utils.DisplayUtils;

public class DigestsListActivity extends BaseActivity implements LifecycleOwner, IDigestsListView {

    //==============================================================================================
    // Static
    //==============================================================================================

    private final static int GRID_LAYOUT_COLUMNS = 2;


    //==============================================================================================
    // Widgets
    //==============================================================================================

    private RecyclerView contentRecycler;
    private DigestsAdapter adapter;


    //==============================================================================================
    // Activity callbacks
    //==============================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(getPresenter()); // SetObserver

        setContentView(R.layout.activity_digests_list);
        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle(getString(R.string.activity_digests_list_title));

        initView();
        initData();
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
    // IDigestsListView callbacks
    //==============================================================================================

    @Override
    public void onUpdateDataSet(List<DigestItem> dataSet) {
        adapter.setData(dataSet);
        adapter.notifyDataSetChanged();
    }


    //==============================================================================================
    // Protected methods
    //==============================================================================================

    @Override
    protected IDigestsListPresenter getPresenter() {
        return controller().getDigestsListPresenter();
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void initView() {
        adapter = new DigestsAdapter();
        contentRecycler = findViewById(R.id.contentRecycler);
        contentRecycler.setLayoutManager(getLayoutManager());
        contentRecycler.setAdapter(adapter);
    }

    private void initData() {
        getPresenter().prepareDigests();
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return DisplayUtils.isPortrait(this)
                ? new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                : new GridLayoutManager(this, GRID_LAYOUT_COLUMNS);
    }
}
