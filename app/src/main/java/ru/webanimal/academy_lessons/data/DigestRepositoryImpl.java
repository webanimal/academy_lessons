package ru.webanimal.academy_lessons.data;

import java.util.List;

import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.utils.DataUtils;

public class DigestRepositoryImpl implements IDigestRepository {

    @Override
    public List<DigestItem> getDigests() {
        return DataUtils.generateNews();
    }
}
