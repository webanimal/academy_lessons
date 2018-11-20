package ru.webanimal.academy_lessons.ui.common;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private IView viewImpl;
    private boolean isReady;

    private final CompositeDisposable disposables = new CompositeDisposable();


    //==============================================================================================
    // Protected methods
    //==============================================================================================

    protected void setView(IView viewImpl) {
        this.viewImpl = viewImpl;
    }

    protected boolean hasView() {
        return viewImpl != null && isReady;
    }

    protected void setReady(boolean state) {
        Log.d("tag", "setReady:" + state);
        this.isReady = state;
    }

    protected void addDisposable(Disposable d) {
        disposables.add(d);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void clearDisposable() {
        Log.d("tag", "clearDisposable onStop");
        disposables.clear();
    }


    //==============================================================================================
    // Abstract methods
    //==============================================================================================

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onLifecycleChange(LifecycleOwner source, Lifecycle.Event event) {
        Lifecycle.State state = source.getLifecycle().getCurrentState();
        Log.d("tag", "onLifecycleChange state:" + state);
        switch (state) {
            case CREATED:
            case STARTED:
            case RESUMED:
                Log.d("tag", "onLifecycleChange state (2-3-4 cr/st/res):" + state);
                setReady(true);
                break;

            case DESTROYED:
            case INITIALIZED:
                Log.d("tag", "onLifecycleChange state (0-1 init/dest):" + state);
                setReady(false);
                break;
        }
    }
}
