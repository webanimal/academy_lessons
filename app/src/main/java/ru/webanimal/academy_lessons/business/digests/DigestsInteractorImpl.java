package ru.webanimal.academy_lessons.business.digests;

import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.data.DigestsRepositoryImpl;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public class DigestsInteractorImpl implements IDigestsInteractor {

    @Override
    public Single<List<DigestItem>> getDigests() {
        return new DigestsRepositoryImpl().getDigests();
    }
}
