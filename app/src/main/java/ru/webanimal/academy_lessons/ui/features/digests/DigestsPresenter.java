package ru.webanimal.academy_lessons.ui.features.digests;

import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.ui.common.BasePresenter;
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
                // Initial mocking data and a network response
                .take(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    // handle the response
                    Log.d("tag", "test !!! presenter loadData() onNext");
                    if (hasView()) {
                        viewImpl.onUpdateDataSet(data);
                    }
                }, e -> {
                    // handle errors
                    Log.e("tag", "test !!! presenter loadData() onError");
                    e.printStackTrace();
                }, () -> {
                    Log.d("tag", "test !!! presenter loadData() onComplete");
                });

        addDisposable(d);
    }
}
