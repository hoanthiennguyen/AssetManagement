package swd.project.assetmanagement.repository;

import java.util.List;

import swd.project.assetmanagement.model.Asset;

public interface AssetListContract {
    interface Model {

        interface OnFinishedListener {
            void onFinished(List<Asset> movieArrayList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pageNo);

    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Asset> movieArrayList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

    }
}
