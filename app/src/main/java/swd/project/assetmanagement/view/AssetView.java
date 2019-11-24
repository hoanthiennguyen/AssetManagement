package swd.project.assetmanagement.view;

import java.util.List;

import swd.project.assetmanagement.model.Asset;

public interface AssetView {
    void showProgress();

    void hideProgress();

    void setDataToRecyclerView(List<Asset> movieArrayList);

    void onResponseFailure(Throwable throwable);
}
