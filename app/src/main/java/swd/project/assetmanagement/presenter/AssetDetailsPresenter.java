package swd.project.assetmanagement.presenter;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.repository.AssetRepository;
import swd.project.assetmanagement.repository.AssetRepositoryImpl;
import swd.project.assetmanagement.view.AssetDetailsView;
import swd.project.assetmanagement.view.LoadingView;

public class AssetDetailsPresenter {
    AssetRepository assetRepo = new AssetRepositoryImpl();
    AssetDetailsView assetDetailsView;
    LoadingView loadingView;
    public AssetDetailsPresenter(AssetDetailsView assetDetailsView, LoadingView loadingView) {
        this.assetDetailsView = assetDetailsView;
        this.loadingView = loadingView;
    }
    public void fetchAssetDetails(int assetId){
        assetRepo.fetchAssetDetails(assetId, new CallbackData<Asset>() {
            @Override
            public void onSuccess(Asset asset) {
                loadingView.hideProgress();
                assetDetailsView.getAssetDetailsSuccess(asset);
            }

            @Override
            public void onFail(String msg) {
                loadingView.hideProgress();
                assetDetailsView.getAssetDetailsFail(msg);
            }
        });
        loadingView.showProgress();
    }

}
