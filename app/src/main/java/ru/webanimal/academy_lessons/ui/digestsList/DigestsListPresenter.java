package ru.webanimal.academy_lessons.ui.digestsList;

import android.arch.lifecycle.LifecycleObserver;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.data.models.TestWork;
import ru.webanimal.academy_lessons.utils.Application;

public class DigestsListPresenter implements LifecycleObserver, IDigestsListPresenter {

    // TODO (Sergio): add life cycle in the presenter
    // TODO (Sergio): bind/unbind a view

    // TODO (Sergio): clear in onPause()
    private final CompositeDisposable disposables = new CompositeDisposable();


    public void prepareDigests() {

    }

    public List<DigestItem> getDigests() {
        List<DigestItem> digests = new ArrayList<>();

        Disposable d = Application.provides().interactors().getDigestInteractor().getDigests()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(digests::addAll, throwable -> Log.e("tag", throwable.getMessage()));

        disposables.add(d);

        return digests;
    }

    public Observable<String> doSomeWork() {
        return Observable
                .fromCallable(TestWork::doWork)
                .subscribeOn(Schedulers.computation());
    }
}
