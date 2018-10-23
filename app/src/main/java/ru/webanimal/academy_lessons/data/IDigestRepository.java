package ru.webanimal.academy_lessons.data;

import java.util.List;

import ru.webanimal.academy_lessons.data.models.DigestItem;

public interface IDigestRepository {

    List<DigestItem> getDigests();
}
