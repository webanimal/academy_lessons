package ru.webanimal.academy_lessons.business.digests;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.data.digests.DigestsRepositoryImpl;
import ru.webanimal.academy_lessons.data.models.DigestItem;

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
