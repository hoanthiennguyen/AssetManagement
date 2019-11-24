package swd.project.assetmanagement.repository;


import java.util.List;

import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.api_util.CallBackData;

public interface AssetRepository{
    void getAllAsset(CallBackData<List<Asset>> callBack);
    void updateAsset(CallBackData<Asset> callBack);
}
