package ru.webanimal.academy_lessons.business.digests;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.data.models.DigestItem;

public interface IDigestsFacade {

    Observable<List<DigestItem>> data();
}
