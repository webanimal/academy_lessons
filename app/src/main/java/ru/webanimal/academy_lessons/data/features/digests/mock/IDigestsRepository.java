package ru.webanimal.academy_lessons.data.features.digests.mock;

import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;

public interface IDigestsRepository {

    Single<List<DigestItem>> fromDB();
}
