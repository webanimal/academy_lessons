package ru.webanimal.academy_lessons.ui.features.digests;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.ui.common.BasePresenter;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;
import ru.webanimal.academy_lessons.utils.Application;

public class DigestsPresenter extends BasePresenter implements IDigestsPresenter {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private IDigestsView viewImpl = null;


    //==============================================================================================
    // IDigestsPresenter callbacks
    //==============================================================================================

    @Override
    public void bindView(IDigestsView digestsViewImpl) {
        this.viewImpl = digestsViewImpl;
        setView(digestsViewImpl);
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

    private void handleResponse(List<DigestItem> items) {
        // handle the response
        Log.d("tag", "test !!! presenter handleResponse() items:" + items);
        if (hasView()) {
            Log.d("tag", "test !!! presenter handleResponse() hasView");
            viewImpl.onUpdateDataSet(items);
        }
    }

    private void handleErrors(Throwable t) {
        // handle errors
        Log.e("tag", "test !!! presenter loadData() onError");
        t.printStackTrace();
    }
}
