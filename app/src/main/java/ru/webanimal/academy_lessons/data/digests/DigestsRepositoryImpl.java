package ru.webanimal.academy_lessons.data.digests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.data.models.TestWork;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestsRepositoryImpl implements IDigestsRepository {

    private final static int MOCK_DELAY = 2000; // millis


    @Override
    public Observable<List<DigestItem>> getInitial() {
        return Observable
                .just(DataUtils.generateInitial())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<DigestItem>> getDigests() {
        return Observable
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
