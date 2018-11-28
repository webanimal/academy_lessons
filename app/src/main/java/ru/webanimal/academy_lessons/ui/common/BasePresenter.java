package ru.webanimal.academy_lessons.ui.common;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * You have to bind Views to Presenter which implements IPresenter.
 * Not to a BasePresenter class.
 * Please, implement IPresenter interface into your current presenter class.
 * See also {@link ru.webanimal.academy_lessons.ui.features.digests.IDigestsPresenter}
 */
public abstract class BasePresenter {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private IView viewImpl;
    private boolean isReady;

    private final CompositeDisposable disposables = new CompositeDisposable();


    //==============================================================================================
    // LifecycleObserver callbacks
    //==============================================================================================

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private void onLifecycleChange(LifecycleOwner source, Lifecycle.Event event) {
        Lifecycle.State state = source.getLifecycle().getCurrentState();
        switch (state) {
            case STARTED:
            case RESUMED:
                Log.d("tag", "onLifecycleChange state (2-3-4 cr/st/res):" + state);
                setReady(true);
                break;

            case INITIALIZED:
            case CREATED:
            case DESTROYED:
                Log.d("tag", "onLifecycleChange state (0-1 init/dest):" + state);
                setReady(false);
                break;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void clearDisposable() {
        Log.d("tag", "clearDisposable onStop");
        disposables.clear();
    }


    //==============================================================================================
    // Protected methods
    //==============================================================================================

    /**
     * BasePresenter method.
     * Register a View into a Presenter here to control a View's LifeCycle.
     */
    protected void setView(IView viewImpl) {
        this.viewImpl = viewImpl;
    }

    /**
     * BasePresenter method.
     * Check before call any method on a View.
     */
    protected boolean hasView() {
        return viewImpl != null && isReady;
    }

    /**
     * BasePresenter method.
     * Utilize disposables here.
     * @param d a Disposable instance
     */
    protected void addDisposable(Disposable d) {
        disposables.add(d);
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private void setReady(boolean state) {
        Log.d("tag", "setReady:" + state);
        this.isReady = state;
    }
}
