package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.webanimal.academy_lessons.data.common.network.BaseResponse;
import ru.webanimal.academy_lessons.data.common.network.modelsDTO.DigestDTO;
import ru.webanimal.academy_lessons.data.common.network.modelsDTO.ResultsDTO;
import ru.webanimal.academy_lessons.data.features.digests.mock.DigestsRepositoryImpl;
import ru.webanimal.academy_lessons.data.features.digests.network.Categories;
import ru.webanimal.academy_lessons.ui.common.modelsUI.Category;
import ru.webanimal.academy_lessons.ui.common.modelsUI.DigestItem;
import ru.webanimal.academy_lessons.utils.Application;

public class DigestsInteractorImpl implements IDigestsInteractor {

    //==============================================================================================
    // IDigestsInteractor callbacks
    //==============================================================================================

    // fromDB
    @Override
    public Single<List<DigestItem>> fromDB() {
        // FIXME (Sergio): not implemented yet
        return new DigestsRepositoryImpl().fromDB();
    }

    // fromNetwork
    @Override
    public Single<List<DigestItem>> fromNetwork() {
        return Application.provides().data().fromNetwork()
                .digestsRestApi()
                // TODO (Sergio): pass here a category type from the UI
                .call(Categories.at(Categories.DEFAULT_CATEGORY))
                .map(this::fromDTO);
        // TODO (Sergio): how to handle errors here and what i have to send further
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    @NonNull
    private List<DigestItem> fromDTO(BaseResponse<ResultsDTO> response) {
        Log.d("tag", "test !!! fromDTO() BaseResponse<ResultsDTO>:" + response);

        ResultsDTO result = null;
        if (response != null) {
            result = response.getData();
        }
        Log.d("tag", "test !!! fromDTO() ResultsDTO:" + result);

        List<DigestItem> digests = new ArrayList<>();
        if (result != null) {
            Log.d("tag", "test !!! fromDTO() List<DigestDTO>:" + result.getResults());

            for (DigestDTO dto : result.getResults()) {
                DigestItem uio = new DigestItem(
                        dto.getTitle(),
                        // TODO (Sergio): add check if empty
                        dto.getMultimediaDTO().get(dto.DEFAULT_MULTIMEDIA_DTO_FORMAT).getUrl(),
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

    @NonNull
    private List<DigestItem> fromDAO() {
        // stub
        Log.d("tag", "test !!! fromDAO() results:empty");
        List<DigestItem> digests = new ArrayList<>();
        return digests;
    }
}
