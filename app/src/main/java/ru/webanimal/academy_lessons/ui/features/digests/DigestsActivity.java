package ru.webanimal.academy_lessons.ui.features.digests;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;
import ru.webanimal.academy_lessons.ui.common.BaseActivity;
import ru.webanimal.academy_lessons.ui.features.about.AboutActivity;
import ru.webanimal.academy_lessons.ui.features.digests.adapter.DigestsAdapter;
import ru.webanimal.academy_lessons.utils.DisplayUtils;

public class DigestsActivity extends BaseActivity implements IDigestsView {

    //==============================================================================================
    // Static
    //==============================================================================================

    private final static int LAYOUT_DIGESTS_LIST = R.layout.activity_digests_list;
    private final static int LAYOUT_MAIN_MENU = R.menu.menu_news_list;
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

        bindLifecycle();
        setupUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(LAYOUT_MAIN_MENU, menu);
        if (menu != null) {
            getPresenter().createMenu(menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int actionId = item.getItemId();
        if (actionId == R.id.digests_action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return false;
    }


    //==============================================================================================
    // IDigestsView callbacks
    //==============================================================================================


    @Override
    public void onUpdateOptionsMenu(@NonNull Menu menu) {
        super.onCreateOptionsMenu(menu); // Call "onCreateOptionsMenu" twice?
    }

    @Override
    public void onUpdateDataSet(@NonNull List<DigestItem> dataSet) {
        Log.d("tag", "activity onUpdateDataSet()");
        adapter.setData(dataSet);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        // stub
        // TODO (Sergio): show error state
        Log.d("tag", "onError() message:" + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyList(String message) {
        // stub
        // TODO (Sergio): show empty state
        Log.d("tag", "onEmptyList() message:" + message);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    //==============================================================================================
    // Protected methods
    //==============================================================================================

    @Override
    protected void bindLifecycle() {
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

    // TODO (Sergio): add loading states
    // TODO (Sergio): add pull-to-refresh logic
    // TODO (Sergio): add "categories from menu" selector
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
        RecyclerView recycler = findViewById(R.id.contentRecycler);
        recycler.setLayoutManager(getLayoutManager());
        recycler.setAdapter(adapter);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        return DisplayUtils.isPortrait(this)
                ? new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                : new GridLayoutManager(this, GRID_LAYOUT_COLUMNS);
    }
}
