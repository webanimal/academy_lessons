package ru.webanimal.academy_lessons.ui.features.digests;

import android.support.annotation.NonNull;
import android.view.Menu;

import ru.webanimal.academy_lessons.ui.common.IPresenter;

public interface IDigestsPresenter extends IPresenter {

    /**
     * To attach a View to a Presenter. To listen to Presenter's commands.
     */
    void bindView(@NonNull IDigestsView viewImpl);

    /**
     * View asks a Presenter to create an action Menu.
     */
    void createMenu(@NonNull Menu menu);

    /**
     * View asks a Presenter to load a default data.
     */
    void loadDefaultData();

    /**
     * View asks a Presenter to load a data for a chosen category name.
     */
    void loadData(@NonNull String categoryName);
}
