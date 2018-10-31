package ru.webanimal.academy_lessons.ui;

import ru.webanimal.academy_lessons.ui.digestsList.DigestsListPresenter;
import ru.webanimal.academy_lessons.ui.digestsList.IDigestsListPresenter;

public class BaseController {

    //==============================================================================================
    // Static
    //==============================================================================================

    private static BaseController instance;
    public static BaseController get() {
        if (instance == null) {
            instance = new BaseController();
        }

        return instance;
    }


    //==============================================================================================
    // Fields
    //==============================================================================================

    private final DigestsListPresenter digestsListPresenter;


    //==============================================================================================
    // Constructor
    //==============================================================================================

    private BaseController() {
        this.digestsListPresenter = new DigestsListPresenter();
    }


    //==============================================================================================
    // Presenter getters
    //==============================================================================================

    public IDigestsListPresenter getDigestsListPresenter() {
        return digestsListPresenter;
    }


    //==============================================================================================
    // Lifecycle owners
    //==============================================================================================

    public DigestsListPresenter getDigestsListObserver() {
        return digestsListPresenter;
    }
}
