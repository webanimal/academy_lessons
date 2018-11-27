package ru.webanimal.academy_lessons.ui.features.digests;

import android.util.Log;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.business.common.UnknownException;
import ru.webanimal.academy_lessons.data.common.network.TwoPiecesContainer;
import ru.webanimal.academy_lessons.data.features.digests.network.Errors.BadDigestsResponseException;
import ru.webanimal.academy_lessons.data.features.digests.network.Errors.NoDigestsResponseException;
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

    private void handleResponse(TwoPiecesContainer<List<DigestItem>> container) {
        Log.d("tag", "test !!! presenter handleResponse() items:" + container);
        if (container.getSecond() != null) {
            handleErrors(container.getSecond());
            return;
        }

        List<DigestItem> items = container.getFirst();
        if (items.size() == 0) {
            handleErrors(new NoDigestsResponseException());
            return;
        }

        if (hasView()) {
            viewImpl.onUpdateDataSet(items);
        }
    }

    private void handleErrors(Throwable t) {
        Log.e("tag", "test !!! presenter loadData() onError");
        if (hasView()) {
            if (t instanceof NoDigestsResponseException) {
                viewImpl.onEmptyList(t.getMessage());
                return;
            }

            if (t instanceof BadDigestsResponseException || t instanceof UnknownException) {
                viewImpl.onError(t.getMessage());
                return;
            }

            // Any other exception
            viewImpl.onError(t.getMessage());
        }
    }
}
