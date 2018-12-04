package ru.webanimal.academy_lessons.data.features.digests.db;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;

public interface IDigestsDb {

    Observable<List<DigestItem>> fromDB(@NonNull String categoryName);
}
