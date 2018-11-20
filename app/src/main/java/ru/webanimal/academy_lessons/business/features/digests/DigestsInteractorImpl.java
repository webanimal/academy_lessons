package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.data.common.network.dto.DigestDTO;
import ru.webanimal.academy_lessons.data.common.network.dto.ResultsDTO;
import ru.webanimal.academy_lessons.data.features.digests.mock.DigestsRepositoryImpl;
import ru.webanimal.academy_lessons.data.features.digests.network.Categories;
import ru.webanimal.academy_lessons.ui.common.modelsUI.Category;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;
import ru.webanimal.academy_lessons.utils.Application;

public class DigestsInteractorImpl implements IDigestsInteractor {

    //==============================================================================================
    // IDigestsInteractor callbacks
    //==============================================================================================

    // room or mocking
    @Override
    public Observable<List<DigestItem>> getInitial() {
        return new DigestsRepositoryImpl().getDigests();
    }

    // room or network
    @Override
    public Observable<List<DigestItem>> getDigests() {
        return Application.provides().dataFrom().network()
                .digestsRestApi()
                .call(Categories.at(23))
                .flatMap(response -> Observable.just(fromDTO(response.getData())));
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    @NonNull
    private List<DigestItem> fromDTO(ResultsDTO results) {
        // List<DigestDTO> response
        List<DigestItem> result = new ArrayList<>();

        if (results != null) {
            for (DigestDTO dto : results.getDigests()) {
                DigestItem uio = new DigestItem(
                        dto.getTitle(),
                        dto.getMultimediaDTO().get(0).getUrl(),
                        new Category(Categories.idFor(dto.getCategory()), dto.getCategory()),
                        dto.getDate(),
                        dto.getShortText(),
                        dto.getFullText()
                );
                result.add(uio);
            }
        }

        return result;
    }
}
