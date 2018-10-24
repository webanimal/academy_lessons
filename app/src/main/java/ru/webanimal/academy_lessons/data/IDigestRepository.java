package ru.webanimal.academy_lessons.data;

import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public interface IDigestRepository {

    Single<List<DigestItem>> getDigests();
}
