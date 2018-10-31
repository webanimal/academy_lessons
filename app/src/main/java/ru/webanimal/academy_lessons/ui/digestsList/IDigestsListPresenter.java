package ru.webanimal.academy_lessons.ui.digestsList;

import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.data.models.DigestItem;
import ru.webanimal.academy_lessons.ui.IBasePresenter;

public interface IDigestsListPresenter extends IBasePresenter {

    void prepareDigests();

    List<DigestItem> getDigests();

    Observable<String> doSomeWork();
}
