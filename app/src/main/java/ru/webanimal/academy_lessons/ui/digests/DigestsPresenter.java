package ru.webanimal.academy_lessons.ui.digests;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import ru.webanimal.academy_lessons.ui.BasePresenter;
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
        Disposable d = Application.provides().interactors().digests().data()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    if (hasView()) {
                        viewImpl.onUpdateDataSet(data);
                    }
                }, throwable -> {
                    Log.e("tag", throwable.getMessage());
                });

        addDisposable(d);
    }
}
