package ru.webanimal.academy_lessons.business;

import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.data.DigestRepositoryImpl;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public class DigestInteractorImpl implements IDigestInteractor {

    @Override
    public Single<List<DigestItem>> getDigests() {
        return new DigestRepositoryImpl().getDigests();
    }
}
