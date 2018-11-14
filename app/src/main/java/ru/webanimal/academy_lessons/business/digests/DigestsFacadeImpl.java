package ru.webanimal.academy_lessons.business.digests;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public class DigestsFacadeImpl implements IDigestsFacade {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private IDigestsInteractor interactor;


    //==============================================================================================
    // IDigestsFacade callbacks
    //==============================================================================================

    @Override
    public Observable<List<DigestItem>> data() {
        return getInteractor().getDigests().startWith(getInteractor().getInitial());
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    private IDigestsInteractor getInteractor() {
        if (interactor == null) {
            interactor = new DigestsInteractorImpl();
        }

        return interactor;
    }
}
