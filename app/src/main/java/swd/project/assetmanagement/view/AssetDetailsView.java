package swd.project.assetmanagement.view;

import swd.project.assetmanagement.model.Asset;

public interface AssetDetailsView {
    void getAssetDetailsSuccess(Asset asset);
    void getAssetDetailsFail(String s);
}
