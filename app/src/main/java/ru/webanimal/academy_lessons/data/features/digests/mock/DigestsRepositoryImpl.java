package ru.webanimal.academy_lessons.data.features.digests.mock;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestsRepositoryImpl implements IDigestsRepository {

    //==============================================================================================
    // IDigestsRepository callbacks
    //==============================================================================================

    // From ROOM. Not implemented yet.
    @Override
    public Observable<List<DigestItem>> fromDB() {
        return Observable
                .just(DataUtils.generateNews());
    }
}
