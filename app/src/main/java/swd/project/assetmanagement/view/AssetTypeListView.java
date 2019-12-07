package swd.project.assetmanagement.view;

import java.util.List;

import swd.project.assetmanagement.model.AssetType;

public interface AssetTypeListView {
    void onSuccessFetchAssetType(List<AssetType> movieArrayList);
    void onFailureFetchAssetType(String throwable);
}
