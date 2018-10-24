package ru.webanimal.academy_lessons.business;

import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public interface IDigestInteractor {

    Single<List<DigestItem>> getDigests();
}
