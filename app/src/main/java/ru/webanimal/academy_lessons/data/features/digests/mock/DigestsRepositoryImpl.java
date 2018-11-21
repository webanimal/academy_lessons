package ru.webanimal.academy_lessons.data.features.digests.mock;

import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestsRepositoryImpl implements IDigestsRepository {

    //==============================================================================================
    // IDigestsRepository callbacks
    //==============================================================================================

    // From ROOM. Not implemented yet.
    @Override
    public Single<List<DigestItem>> fromDB() {
        return Single
                .just(DataUtils.generateNews());
    }
}
