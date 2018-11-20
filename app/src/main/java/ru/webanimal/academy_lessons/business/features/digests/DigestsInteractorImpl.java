package ru.webanimal.academy_lessons.business.features.digests;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.data.features.digests.mock.DigestsRepositoryImpl;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;

public class DigestsInteractorImpl implements IDigestsInteractor {

    @Override
    public Observable<List<DigestItem>> getInitial() {
        return new DigestsRepositoryImpl().getInitial();
    }

    @Override
    public Observable<List<DigestItem>> getDigests() {
        return new DigestsRepositoryImpl().getDigests();
    }
}
