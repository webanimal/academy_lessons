package ru.webanimal.academy_lessons.data.features.digests.db;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestsDbImpl implements IDigestsDb {

    //==============================================================================================
    // IDigestsDb callbacks
    //==============================================================================================

    // From ROOM. Not implemented yet.
    @Override
    public Observable<List<DigestItem>> fromDB() {
        return Observable
                .just(DataUtils.generateNews());
    }
}
