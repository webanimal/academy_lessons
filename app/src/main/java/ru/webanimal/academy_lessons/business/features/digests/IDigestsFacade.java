package ru.webanimal.academy_lessons.business.features.digests;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.utils.containers.TwoPiecesContainer;
import ru.webanimal.academy_lessons.ui.common.modelsUIO.DigestItem;

public interface IDigestsFacade {

    Observable<TwoPiecesContainer<List<DigestItem>>> getDigests();
}
