package swd.project.assetmanagement.view;

import java.util.List;

import swd.project.assetmanagement.model.Asset;

public interface AssetListView {


    void onSuccessFetchAssetList(List<Asset> movieArrayList);

    void onFailureFetchAssetList(String throwable);
}
