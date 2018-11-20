package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
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

    // room or fromNetwork
    @Override
    public Observable<List<DigestItem>> getDigests() {
        return Application.provides().data().fromNetwork()
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
        List<DigestItem> digests = new ArrayList<>();

        if (results != null) {
            Log.d("tag", "test !!! " + results.getDigests().toString());

            for (DigestDTO dto : Arrays.asList(results.getDigests())) {
                DigestItem uio = new DigestItem(
                        dto.getTitle(),
                        // TODO (Sergio): add check if empty
                        dto.getMultimediaDTO()[2].getUrl(),
                        new Category(Categories.idFor(dto.getCategory()), dto.getCategory()),
                        dto.getDate(),
                        dto.getShortText(),
                        dto.getFullText()
                );
                digests.add(uio);
            }
        }

        return digests;
    }
}
