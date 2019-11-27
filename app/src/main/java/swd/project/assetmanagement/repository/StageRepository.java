package swd.project.assetmanagement.repository;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.dto.ResponseDTO;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.model.Stage;

public interface StageRepository {
    void fetchListStage(int assetId, CallbackData<List<Stage>> callback);
    void addNewStage(int assetId, Stage newStage, CallbackData<Stage> callback);
}
