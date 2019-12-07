package swd.project.assetmanagement.repository;


import java.util.List;

import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.api_util.CallbackData;

public interface AssetRepository{
    void fetchListAsset(CallbackData<List<Asset>> callBack);
    void updateAsset(CallbackData<Asset> callBack);
    void fetchAssetDetails(int assetId, CallbackData<Asset> callback);
    void fiterAsset(String room, String status, Long assetTypeId, CallbackData<List<Asset>> callBack);
}
