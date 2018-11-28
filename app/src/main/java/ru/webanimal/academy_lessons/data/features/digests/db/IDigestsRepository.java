package ru.webanimal.academy_lessons.data.features.digests.db;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;

public interface IDigestsRepository {

    Observable<List<DigestItem>> fromDB();
}
