package swd.project.assetmanagement.repository;


import java.util.List;

import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.api_util.CallbackData;

public interface AssetRepository{
    void fetchListAsset(int employeeId, CallbackData<List<Asset>> callBack);
    void updateAsset(CallbackData<Asset> callBack);
    void fetchAssetDetails(int assetId, CallbackData<Asset> callback);
    void fiterAsset(int employeeId, String room, String status, Long assetTypeId, CallbackData<List<Asset>> callBack);
}
