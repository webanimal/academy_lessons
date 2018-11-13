package ru.webanimal.academy_lessons.ui.digests;

import ru.webanimal.academy_lessons.ui.IPresenter;

public interface IDigestsPresenter extends IPresenter {

    /**
     * This method is intended to standardize binding process of a view to a presenter.
     */
    void bindView(IDigestsView viewImpl);

    void loadInitialData();

    void loadData();
}
