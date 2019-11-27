package swd.project.assetmanagement.presenter;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.repository.AssetRepository;
import swd.project.assetmanagement.repository.AssetRepositoryImpl;
import swd.project.assetmanagement.view.AssetDetailsView;
import swd.project.assetmanagement.view.AssetListView;
import swd.project.assetmanagement.view.LoadingView;

public class AssetListPresenter {
    AssetRepository assetRepo = new AssetRepositoryImpl();
    AssetListView assetListView;
    AssetDetailsView assetDetailsView;
    LoadingView loadingView;
    int pageNumber;



    public AssetListPresenter(AssetListView assetListView, LoadingView loadingView) {
        this.assetListView = assetListView;
        this.loadingView = loadingView;
    }

    public void fetchListAssetFromServer() {
        assetRepo.fetchListAsset(new CallbackData<List<Asset>>() {
            @Override
            public void onSuccess(List<Asset> assetList) {
                loadingView.hideProgress();
                assetListView.onSuccessFetchAssetList(assetList);
            }

            @Override
            public void onFail(String msg) {
                loadingView.hideProgress();
                assetListView.onFailureFetchAssetList(msg);
            }
        });
        loadingView.showProgress();
    }



}
