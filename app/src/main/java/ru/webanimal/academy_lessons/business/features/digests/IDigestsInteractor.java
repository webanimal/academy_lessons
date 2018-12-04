package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.utils.containers.TwoPiecesContainer;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;

public interface IDigestsInteractor {

    Observable<TwoPiecesContainer<List<DigestItem>>> fromDB(@NonNull String categoryName);

    Observable<TwoPiecesContainer<List<DigestItem>>> fromNetwork(@NonNull String categoryName);

    @NonNull
    String[] getDigestsCategories();
}
