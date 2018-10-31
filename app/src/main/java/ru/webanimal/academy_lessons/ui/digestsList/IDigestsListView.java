package ru.webanimal.academy_lessons.ui.digestsList;

import java.util.List;

import ru.webanimal.academy_lessons.data.models.DigestItem;

public interface IDigestsListView {

    void onUpdateDataSet(List<DigestItem> dataSet);
}
