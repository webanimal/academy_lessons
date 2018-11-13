package ru.webanimal.academy_lessons.data;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.data.models.TestWork;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestsRepositoryImpl implements IDigestsRepository {

    private final static int MOCK_DELAY = 3000; // millis

    @Override
    public Single<List<DigestItem>> getDigests() {
        return Single
                .just(DataUtils.generateNews())
                .delay(MOCK_DELAY, TimeUnit.MICROSECONDS)
                .map(data -> {
                    doSomeWork()
                            .subscribe(someWorkResult -> {
                                Log.d("tag", "Success. Did some computation work");

                            }, e -> {
                                Log.d("tag", "Error. Tried to do some computation work");
                                e.printStackTrace();
                            });

                    return data;
                });
    }

    private Observable<String> doSomeWork() {
        return Observable
                .fromCallable(TestWork::doWork)
                .subscribeOn(Schedulers.computation());
    }
}
