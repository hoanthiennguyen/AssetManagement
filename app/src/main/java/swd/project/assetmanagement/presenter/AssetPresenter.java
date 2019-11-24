package swd.project.assetmanagement.presenter;

import java.util.List;

import swd.project.assetmanagement.api_util.CallBackData;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.repository.AssetRepository;
import swd.project.assetmanagement.repository.AssetRepositoryImpl;
import swd.project.assetmanagement.view.AssetView;

public class AssetPresenter {
    AssetRepository assetRepo = new AssetRepositoryImpl();
    AssetView view;
    int pageNumber;

    public AssetPresenter(AssetView view) {
        this.view = view;
    }


    public void requestDataFromServer() {
        assetRepo.getAllAsset(new CallBackData<List<Asset>>() {
            @Override
            public void onSuccess(List<Asset> assetList) {
                onFinished(assetList);
            }

            @Override
            public void onFail(String message) {
                onFailure(new Throwable(message));
            }
        });
    }

    public void onFinished(List<Asset> assetList) {
        view.hideProgress();
        view.setDataToRecyclerView(assetList);
    }

    public void onFailure(Throwable t) {
        view.hideProgress();
        view.onResponseFailure(t);

    }
}
