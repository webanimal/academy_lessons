package ru.webanimal.academy_lessons.ui.digestsList;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
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

    //==============================================================================================
    // Fields
    //==============================================================================================

    private final CompositeDisposable disposables = new CompositeDisposable();
    private boolean isCreated;


    //==============================================================================================
    // LifecycleObserver callbacks
    //==============================================================================================

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void checkIfCreated(LifecycleOwner source, Lifecycle.Event event) {
        Lifecycle.State state = source.getLifecycle().getCurrentState();
        switch (state) {
            case CREATED:
            case STARTED:
            case RESUMED:
                isCreated = true;
                break;

            case DESTROYED:
            case INITIALIZED:
                isCreated = false;
                break;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void clearDisposables() {
        disposables.clear();
    }


    //==============================================================================================
    // IDigestsListPresenter callbacks
    //==============================================================================================

    @Override
    public void prepareDigests() {
        List<DigestItem> digests = new ArrayList<>();

        Disposable d = Application.provides().interactors().getDigestInteractor().getDigests()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(data -> {
                    doSomeWork()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(someWorkResult -> {
                                Log.d("tag", "Success. Did some computation work");

                            }, e -> {
                                Log.d("tag", "Error. Tried to do some computation work");
                                e.printStackTrace();
                            });

                    return data;
                })
                .subscribe(digests::addAll, throwable -> Log.e("tag", throwable.getMessage()));

        // TODO (Sergio): add a callback from here to the IDigestsListView.onUpdateDataSet(digests)
        // isCreated ? send a callback : else nothing

        disposables.add(d);
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private Observable<String> doSomeWork() {
        return Observable
                .fromCallable(TestWork::doWork)
                .subscribeOn(Schedulers.computation());
    }
}
