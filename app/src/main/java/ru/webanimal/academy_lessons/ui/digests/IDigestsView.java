package ru.webanimal.academy_lessons.ui.digests;

import java.util.List;

import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.ui.IView;

public interface IDigestsView extends IView {

    void onUpdateDataSet(List<DigestItem> dataSet);
}
