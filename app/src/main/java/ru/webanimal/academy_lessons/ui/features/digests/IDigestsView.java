package ru.webanimal.academy_lessons.ui.features.digests;

import java.util.List;

import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;
import ru.webanimal.academy_lessons.ui.common.IView;

public interface IDigestsView extends IView {

    void onUpdateDataSet(List<DigestItem> dataSet);
}
