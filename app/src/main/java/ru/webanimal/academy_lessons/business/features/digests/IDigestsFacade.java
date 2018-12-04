package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.utils.containers.TwoPiecesContainer;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;

public interface IDigestsFacade {

    Observable<TwoPiecesContainer<List<DigestItem>>> getDefaultDigests();

    Observable<TwoPiecesContainer<List<DigestItem>>> getDigests(@NonNull String categoryName);

    @NonNull
    String[] getDigestsCategories();
}
