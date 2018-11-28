package ru.webanimal.academy_lessons.ui.features.digests;

import java.util.List;

import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;
import ru.webanimal.academy_lessons.ui.common.IView;

public interface IDigestsView extends IView {

    void onUpdateDataSet(List<DigestItem> dataSet);

    void onError(String message);

    void onEmptyList(String message);
}
