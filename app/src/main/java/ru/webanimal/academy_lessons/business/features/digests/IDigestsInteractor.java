package ru.webanimal.academy_lessons.business.features.digests;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;

public interface IDigestsInteractor {

    Observable<List<DigestItem>> fromDB();

    Observable<List<DigestItem>> fromNetwork();
}
