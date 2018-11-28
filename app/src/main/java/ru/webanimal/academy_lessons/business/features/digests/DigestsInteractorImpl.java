package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.webanimal.academy_lessons.utils.CollectionUtils;
import ru.webanimal.academy_lessons.utils.containers.TwoPiecesContainer;
import ru.webanimal.academy_lessons.data.common.network.DTO.DigestDTO;
import ru.webanimal.academy_lessons.data.common.network.DTO.ResultsDTO;
import ru.webanimal.academy_lessons.data.features.digests.db.DigestsDbImpl;
import ru.webanimal.academy_lessons.data.features.digests.network.errors.BadDigestsResponseException;
import ru.webanimal.academy_lessons.data.features.digests.network.errors.NoDigestsResponseException;
import ru.webanimal.academy_lessons.ui.common.UIO.Category;
import ru.webanimal.academy_lessons.ui.common.UIO.DigestItem;
import ru.webanimal.academy_lessons.utils.Application;

public class DigestsInteractorImpl implements IDigestsInteractor {

    //==============================================================================================
    // IDigestsInteractor callbacks
    //==============================================================================================

    // fromDB
    @Override
    public Observable<TwoPiecesContainer<List<DigestItem>>> fromDB() {
        // FIXME (Sergio): not implemented yet
        return new DigestsDbImpl().fromDB()
                .map(list -> new TwoPiecesContainer<>(list, null));
    }

    // fromNetwork
    @Override
    public Observable<TwoPiecesContainer<List<DigestItem>>> fromNetwork() {
        return Application.provides().data().fromNetwork()
                .digestsRestApi()
                .call(fromHardcodedDefaultCategoryName())
                .map(response -> {
                    Throwable t = null;
                    if (!response.isSuccessful()) {
                        t = new BadDigestsResponseException();
                    }

                    final ResultsDTO res = response.body();
                    if (res == null || CollectionUtils.isEmpty(res.getResults())) {
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
        List<DigestItem> digests = new ArrayList<>();
        if (resultsDTO != null && !CollectionUtils.isEmpty(resultsDTO.getResults())) {
            for (DigestDTO dto : resultsDTO.getResults()) {
                DigestItem uio = new DigestItem(
                        dto.getTitle(),
                        CollectionUtils.isEmpty(dto.getMultimediaDTO())
                                ? ""
                                : dto.getMultimediaDTO().get(dto.DEFAULT_MULTIMEDIA_DTO_FORMAT).getUrl(),
                        new Category(fromHardcodedCategoryIdForName(dto.getCategory()), dto.getCategory()),
                        dto.getDate(),
                        dto.getShortText(),
                        dto.getFullText()
                );
                digests.add(uio);
            }
        }

        return digests;
    }

    //==============================================================================================

    @NonNull
    private List<DigestItem> fromDAO() {
        // stub
        Log.d("tag", "test !!! fromDAO() results:empty");
        List<DigestItem> digests = new ArrayList<>();
        return digests;
    }

    //==============================================================================================

    @NonNull
    private int fromHardcodedDefaultCategoryId() {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .getDefaultCategoryId();
    }

    @NonNull
    private int fromHardcodedCategoryIdForName(String categoryName) {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .findCategoryIdForName(categoryName);
    }

    @NonNull
    private String fromHardcodedDefaultCategoryName() {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .getDefaultCategoryName();
    }

    @NonNull
    private String fromHardcodedCategoryNameForId(int categoryId) {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .findCategoryNameForId(categoryId);
    }
}
