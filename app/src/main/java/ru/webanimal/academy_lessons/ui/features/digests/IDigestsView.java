package ru.webanimal.academy_lessons.ui.features.digests;

import android.support.annotation.NonNull;
import android.view.Menu;

import java.util.List;

import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;
import ru.webanimal.academy_lessons.ui.common.IView;

public interface IDigestsView extends IView {

    void onUpdateOptionsMenu(@NonNull Menu menu);

    void onUpdateDataSet(@NonNull List<DigestItem> dataSet);

    void onError(String message);

    void onEmptyList(String message);
}
