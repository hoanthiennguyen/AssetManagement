package swd.project.assetmanagement.repository;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.AssetType;

public interface AssetTypeRepository {
    void fetchListAssetType(CallbackData<List<AssetType>> callBack);
}
