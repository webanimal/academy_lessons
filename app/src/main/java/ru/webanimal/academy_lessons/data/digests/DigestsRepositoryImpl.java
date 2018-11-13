package ru.webanimal.academy_lessons.data.digests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.data.models.TestWork;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestsRepositoryImpl implements IDigestsRepository {

    private final static int MOCK_DELAY = 3000; // millis

    @Override
    public Single<List<DigestItem>> getInitial() {
        return Single
                .just(DataUtils.generateInitial())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<DigestItem>> getDigests() {
        return Single
                .just(DataUtils.generateNews())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .delay(MOCK_DELAY, TimeUnit.MICROSECONDS)
                .map(data -> {
                    TestWork.doWork();
                    return data;
                });
    }
}
