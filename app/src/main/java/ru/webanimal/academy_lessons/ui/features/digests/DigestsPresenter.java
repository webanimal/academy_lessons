package ru.webanimal.academy_lessons.ui.features.digests;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.R;
import ru.webanimal.academy_lessons.business.common.UnknownException;
import ru.webanimal.academy_lessons.utils.DrawableUtils;
import ru.webanimal.academy_lessons.utils.containers.TwoPiecesContainer;
import ru.webanimal.academy_lessons.data.features.digests.network.errors.BadDigestsResponseException;
import ru.webanimal.academy_lessons.data.features.digests.network.errors.NoDigestsResponseException;
import ru.webanimal.academy_lessons.ui.common.BasePresenter;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;
import ru.webanimal.academy_lessons.utils.Application;

public class DigestsPresenter extends BasePresenter implements IDigestsPresenter {

    //==============================================================================================
    // Fields
    //==============================================================================================

    // TODO (Sergio): possible to implement a commands queue
    // if (!hasView) { addCommand; }
    // if (event with state 'ready') { applyCommands; }
    private IDigestsView digestsViewImpl = null;


    //==============================================================================================
    // IDigestsPresenter callbacks
    //==============================================================================================

    @Override
    public void bindView(@NonNull IDigestsView digestsViewImpl) {
        this.digestsViewImpl = digestsViewImpl;
        super.setView(digestsViewImpl);
    }

    @Override
    public void createMenu(@NonNull Menu menu) {
        handleMenuCreation(menu);
    }

    @Override
    public void loadData() {
        final Disposable d = Application.provides().interactors().getDigestsInteractor()
                .getDigests()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleErrors);

        addDisposable(d);
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void handleMenuCreation(Menu menu) {
        int groupId = R.id.digests_action_group_categories;

        Resources res = Application.provides().context().getResources();
        int[] actions = res.getIntArray(R.array.digests_actions);
        String[] categories = Application.provides().data().fromHardcore().forDigests().getAllCategories();
        int cap = Math.min(actions.length, categories.length);

        SubMenu sub = menu.getItem(0).getSubMenu();
        for (int i = 0; i < cap; i++) {
            if (sub != null) {
                sub.add(groupId, actions[i], i + 10, categories[i]);

            } else {
                menu.add(groupId, actions[i], i + 10, categories[i]);
            }
        }

        int colorId = res.getColor(R.color.colorWhite);
        MenuItem item = menu.findItem(R.id.digests_action_categories);
        DrawableUtils.setMenuIconTintColor(item, colorId);
        item = menu.findItem(R.id.digests_action_about);
        DrawableUtils.setMenuIconTintColor(item, colorId);

        if (hasView()) {
            digestsViewImpl.onUpdateOptionsMenu(menu);
        }
    }

    private void handleResponse(TwoPiecesContainer<List<DigestItem>> container) {
        Log.d("tag", "test !!! presenter handleResponse() items:" + container);
        if (container.getSecond() != null) {
            handleErrors(container.getSecond());
            return;
        }

        List<DigestItem> items = container.getFirst();
        if (items == null || items.size() == 0) {
            handleErrors(new NoDigestsResponseException());
            return;
        }

        if (hasView()) {
            digestsViewImpl.onUpdateDataSet(items);
        }
    }

    private void handleErrors(Throwable t) {
        Log.e("tag", "test !!! presenter loadData() onError");
        if (hasView()) {
            if (t instanceof NoDigestsResponseException) {
                digestsViewImpl.onEmptyList(t.getMessage());
                return;
            }

            if (t instanceof BadDigestsResponseException || t instanceof UnknownException) {
                digestsViewImpl.onError(t.getMessage());
                return;
            }

            // Any other exception
            digestsViewImpl.onError(t.getMessage());
        }
    }
}
