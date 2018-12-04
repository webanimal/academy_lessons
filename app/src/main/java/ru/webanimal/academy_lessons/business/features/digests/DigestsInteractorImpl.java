package ru.webanimal.academy_lessons.business.features.digests;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    public Observable<TwoPiecesContainer<List<DigestItem>>> fromDB(@NonNull String categoryName) {
        // FIXME (Sergio): not implemented yet
        return new DigestsDbImpl().fromDB(categoryName)
                .map(list -> new TwoPiecesContainer<>(list, null));
    }

    // fromNetwork
    @Override
    public Observable<TwoPiecesContainer<List<DigestItem>>> fromNetwork(@NonNull String categoryName) {
        return Application.provides().data().fromNetwork()
                .digestsRestApi()
                .call(approveOrGetHardcodedDefaultCategoryName(categoryName))
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

    @NonNull
    @Override
    public String[] getDigestsCategories() {
        return getDigestsCategoryNames();
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
                        new Category(getHardcodedCategoryIdForName(dto.getCategory()), dto.getCategory()),
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
        return new ArrayList<>();
    }

    //==============================================================================================

    private int getHardcodedDefaultCategoryId() {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .getDefaultCategoryId();
    }

    private int getHardcodedCategoryIdForName(@Nullable String categoryName) {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .getCategoryIdForName(categoryName == null ? "" : categoryName);
    }

    @NonNull
    private String getHardcodedDefaultCategoryName() {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .getDefaultCategoryName();
    }

    @NonNull
    private String getHardcodedCategoryNameForId(int categoryId) {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .getCategoryNameForId(categoryId);
    }

    @NonNull
    private String approveOrGetHardcodedDefaultCategoryName(@NonNull String categoryName) {
        return Application.provides().data().fromHardcore()
                .forDigests()
                .approveOrGetDefaultCategoryName(categoryName);
    }

    @NonNull
    private String[] getDigestsCategoryNames() {
        return Application.provides().data().fromHardcore().forDigests().getAllCategories();
    }
}
