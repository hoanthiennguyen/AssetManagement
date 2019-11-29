package swd.project.assetmanagement.presenter;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.Stage;
import swd.project.assetmanagement.repository.StageRepository;
import swd.project.assetmanagement.repository.StageRepositoryImpl;
import swd.project.assetmanagement.view.StageListView;

public class StageListPresenter {
    StageRepository stageRepository = new StageRepositoryImpl();
    StageListView stageListView;

    public StageListPresenter(StageListView stageListView) {
        this.stageListView = stageListView;
    }
    public void fetchListStage(int assetId){
        stageRepository.fetchListStage(assetId, new CallbackData<List<Stage>>() {
            @Override
            public void onSuccess(List<Stage> stages) {
                stageListView.onSuccessFetchStageList(stages);
            }

            @Override
            public void onFail(String msg) {
                stageListView.onFailFetchStageList(msg);
            }
        });
    }
    public void addNewStage(int assetId, Stage newStage){
        stageRepository.addNewStage(assetId, newStage, new CallbackData<Stage>() {
            @Override
            public void onSuccess(Stage stage) {
                stageListView.onSuccessAddNewStage(stage);
            }

            @Override
            public void onFail(String msg) {
                stageListView.onFailAddNewStage(msg);
            }
        });
    }
}
