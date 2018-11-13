package ru.webanimal.academy_lessons.ui.digests;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    public void bindView(IDigestsView viewImpl) {
        this.viewImpl = viewImpl;
        setView(viewImpl);
    }

    @Override
    public void loadInitialData() {
        Disposable d = Application.provides().interactors().digestsInteractor().getInitial()
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

    @Override
    public void loadData() {
        Disposable d = Application.provides().interactors().digestsInteractor().getDigests()
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
