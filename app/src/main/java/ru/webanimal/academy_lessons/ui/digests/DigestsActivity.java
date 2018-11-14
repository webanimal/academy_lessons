package ru.webanimal.academy_lessons.ui.digests;

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

public class DigestsActivity extends BaseActivity implements IDigestsView {

    //==============================================================================================
    // Static
    //==============================================================================================

    private final static int LAYOUT_DIGESTS_LIST = R.layout.activity_digests_list;
    private final static int GRID_LAYOUT_COLUMNS = 2;


    //==============================================================================================
    // Fields
    //==============================================================================================

    private DigestsAdapter adapter;


    //==============================================================================================
    // Activity callbacks
    //==============================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT_DIGESTS_LIST);

        bindView();
        setupUI();
        getPresenter().loadData();
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
    // IDigestsView callbacks
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
    protected void bindView() {
        getLifecycle().addObserver(getPresenter());
        getPresenter().bindView(this);
    }

    @Override
    protected IDigestsPresenter getPresenter() {
        return manager().digests();
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void setupUI() {
        setupTitle();
        setupRecycler();
    }

    private void setupTitle() {
        setSupportActionBar(findViewById(R.id.toolbar));
        setTitle(getString(R.string.activity_digests_list_title));
    }

    private void setupRecycler() {
        adapter = new DigestsAdapter();
        RecyclerView contentRecycler = findViewById(R.id.contentRecycler);
        contentRecycler.setLayoutManager(getLayoutManager());
        contentRecycler.setAdapter(adapter);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return DisplayUtils.isPortrait(this)
                ? new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                : new GridLayoutManager(this, GRID_LAYOUT_COLUMNS);
    }

    // TODO (Sergio): add loading state
}
