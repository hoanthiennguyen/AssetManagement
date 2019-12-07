package swd.project.assetmanagement.presenter;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.AssetType;
import swd.project.assetmanagement.repository.AssetTypeRepository;
import swd.project.assetmanagement.repository.AssetTypeRepositoryImpl;
import swd.project.assetmanagement.view.AssetTypeListView;
import swd.project.assetmanagement.view.LoadingView;

public class AssetTypeListPresenter {
    AssetTypeRepository repo = new AssetTypeRepositoryImpl();
    AssetTypeListView listView;
    LoadingView loadingView;

    public AssetTypeListPresenter(AssetTypeListView listView, LoadingView loadingView) {
        this.listView = listView;
        this.loadingView = loadingView;
    }

    public void fetchAssetTypeFromServer() {
        repo.fetchListAssetType(new CallbackData<List<AssetType>>() {
            @Override
            public void onSuccess(List<AssetType> assetTypes) {
                loadingView.hideProgress();
                listView.onSuccessFetchAssetType(assetTypes);
            }

            @Override
            public void onFail(String msg) {
                loadingView.hideProgress();
                listView.onFailureFetchAssetType(msg);
            }
        });
        loadingView.showProgress();
    }
}
