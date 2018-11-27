package ru.webanimal.academy_lessons.ui.features.digests;

import ru.webanimal.academy_lessons.ui.common.IPresenter;

public interface IDigestsPresenter extends IPresenter {

    /**
     * To give a View's callbacks Access to a Presenter
     */
    void bindView(IDigestsView viewImpl);

    /**
     * IDigestsPresenter command
     */
    void loadData();
}
