package ru.webanimal.academy_lessons.data;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestRepositoryImpl implements IDigestRepository {

    private final static int MOCK_DELAY = 3000; // millis

    @Override
    public Single<List<DigestItem>> getDigests() {
        return Single
                .just(DataUtils.generateNews())
                .delay(MOCK_DELAY, TimeUnit.MICROSECONDS);
    }
}
