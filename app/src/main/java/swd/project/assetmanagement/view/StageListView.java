package swd.project.assetmanagement.view;

import java.util.List;

import swd.project.assetmanagement.model.Stage;

public interface StageListView {
    void onSuccessFetchStageList(List<Stage> stages);
    void onFailFetchStageList(String str);
}
