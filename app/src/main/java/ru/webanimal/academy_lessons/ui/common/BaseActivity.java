package ru.webanimal.academy_lessons.ui.common;

import android.support.v7.app.AppCompatActivity;

import ru.webanimal.academy_lessons.utils.Application;

/**
 * You have to register LifecycleOwner for View which implements IView.
 * Not to a BaseActivity class.
 * Please, implement IView interface into your current view class.
 * See also {@link ru.webanimal.academy_lessons.ui.features.digests.DigestsActivity}
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * Short way to get presenters holden by an Application class.
     * @return a PresenterManager's instance.
     */
    protected PresenterManager manager() {
        return Application.provides().presenters();
    }

    /**
     * Provides access to a presenter's commands.
     * Also, this presenter is a lifecycle observer.
     * Obtain the presenter from a {@link #manager()} here.
     */
    protected abstract IPresenter getPresenter();

    /**
     * This method is intended to standardize binding process of a view to a presenter.
     * Add a LifecycleObserver and bind a View to a Presenter here.
     */
    protected abstract void bindLifecycle();
}
