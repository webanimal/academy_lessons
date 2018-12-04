package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.utils.containers.TwoPiecesContainer;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;

public class DigestsFacadeImpl implements IDigestsFacade {

    //==============================================================================================
    // Fields
    //==============================================================================================

    private IDigestsInteractor interactor;


    //==============================================================================================
    // IDigestsFacade callbacks
    //==============================================================================================

    @Override
    public Observable<TwoPiecesContainer<List<DigestItem>>> getDefaultDigests() {
        return getInteractor().fromNetwork("");
    }

    @Override
    public Observable<TwoPiecesContainer<List<DigestItem>>> getDigests(@NonNull String categoryName) {
        // TODO (Sergio): add here a data source selector (DB, Network)
        return getInteractor().fromNetwork(categoryName);
    }

    @NonNull
    @Override
    public String[] getDigestsCategories() {
        return getInteractor().getDigestsCategories();
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
