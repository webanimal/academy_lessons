package ru.webanimal.academy_lessons.ui.common;

import android.support.v7.app.AppCompatActivity;

import ru.webanimal.academy_lessons.utils.Application;

public abstract class BaseActivity extends AppCompatActivity {

    protected PresenterManager manager() {
        return Application.provides().presenters();
    }

    /**
     * Provides access to a presenter's commands.
     * Also, this presenter is a lifecycle observer.
     * Obtain the presenter from a {@link #manager()}
     */
    protected abstract IPresenter getPresenter();

    /**
     * This method is intended to standardize binding process of a view to a presenter.
     *
     * Set lifecycle observer & fromNetwork presenter here.
     */
    protected abstract void bindView();
}
