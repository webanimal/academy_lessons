package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.utils.containers.TwoPiecesContainer;
import ru.webanimal.academy_lessons.data.common.network.modelsDTO.DigestDTO;
import ru.webanimal.academy_lessons.data.common.network.modelsDTO.ResultsDTO;
import ru.webanimal.academy_lessons.data.features.digests.mock.DigestsRepositoryImpl;
import ru.webanimal.academy_lessons.data.features.digests.network.errors.BadDigestsResponseException;
import ru.webanimal.academy_lessons.data.features.digests.network.Categories;
import ru.webanimal.academy_lessons.data.features.digests.network.errors.NoDigestsResponseException;
import ru.webanimal.academy_lessons.ui.common.modelsUIO.Category;
import ru.webanimal.academy_lessons.ui.common.modelsUIO.DigestItem;
import ru.webanimal.academy_lessons.utils.Application;

public class DigestsInteractorImpl implements IDigestsInteractor {

    //==============================================================================================
    // IDigestsInteractor callbacks
    //==============================================================================================

    // fromDB
    @Override
    public Observable<TwoPiecesContainer<List<DigestItem>>> fromDB() {
        // FIXME (Sergio): not implemented yet
        return new DigestsRepositoryImpl().fromDB()
                .map(list -> new TwoPiecesContainer<>(list, null));
    }

    // fromNetwork
    @Override
    public Observable<TwoPiecesContainer<List<DigestItem>>> fromNetwork() {
        return Application.provides().data().fromNetwork()
                .digestsRestApi()
                // TODO (Sergio): pass here a category type from the UI menu
                .call(Categories.at(Categories.DEFAULT_CATEGORY))
                .map(response -> {
                    Throwable t = null;
                    if (!response.isSuccessful()) {
                        t = new BadDigestsResponseException();
                    }

                    final ResultsDTO res = response.body();
                    if (res == null || res.getResults() == null || res.getResults().size() == 0) {
                        t = new NoDigestsResponseException();
                    }

                    return new TwoPiecesContainer<>(fromDTO(res), t);
                });
    }


    //==============================================================================================
    // Private methods
    //==============================================================================================

    @NonNull
    private List<DigestItem> fromDTO(@Nullable ResultsDTO resultsDTO) {
        Log.d("tag", "test !!! fromDTO() ResultsDTO:" + resultsDTO);
        List<DigestItem> digests = new ArrayList<>();
        if (resultsDTO != null) {
            Log.d("tag", "test !!! fromDTO() List<DigestDTO>:" + resultsDTO.getResults());
            for (DigestDTO dto : resultsDTO.getResults()) {
                Category category = new Category(Categories.idFor(dto.getCategory()), dto.getCategory());
                DigestItem uio = new DigestItem(
                        dto.getTitle(),
                        // TODO (Sergio): add check if multimedia empty
                        dto.getMultimediaDTO().get(dto.DEFAULT_MULTIMEDIA_DTO_FORMAT).getUrl(),
                        category,
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
