package ru.webanimal.academy_lessons.business.digests;

import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public interface IDigestsInteractor {

    Single<List<DigestItem>> getDigests();
}
