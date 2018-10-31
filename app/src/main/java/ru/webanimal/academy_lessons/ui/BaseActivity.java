package ru.webanimal.academy_lessons.ui;

import android.arch.lifecycle.LifecycleObserver;
import android.support.v7.app.AppCompatActivity;

import ru.webanimal.academy_lessons.utils.Application;

public abstract class BaseActivity extends AppCompatActivity {

    protected BaseController controller() {
        return Application.provides().controller();
    }

    protected abstract IBasePresenter getPresenter();

    protected abstract LifecycleObserver getObserver();
}
