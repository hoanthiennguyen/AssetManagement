package swd.project.assetmanagement.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.api_util.RetrofitConfiguration;
import swd.project.assetmanagement.dto.ResponseDTO;
import swd.project.assetmanagement.dto.ResponseListStage;
import swd.project.assetmanagement.dto.ResponseStatus;
import swd.project.assetmanagement.model.Stage;

public class StageRepositoryImpl implements StageRepository{
    StageService stageService = RetrofitConfiguration.getRetrofitAdapter().create(StageService.class);
    @Override
    public void fetchListStage(int assetId, final CallbackData<List<Stage>> callback) {
        Call<ResponseListStage> call = stageService.fetchAssetStages(assetId);
        call.enqueue(new Callback<ResponseListStage>() {
            @Override
            public void onResponse(Call<ResponseListStage> call, Response<ResponseListStage> response) {
                ResponseListStage dto = response.body();
                if(dto.getStatus().equals(ResponseStatus.OK.toString())){
                    callback.onSuccess(dto.getPayload());
                }
                else
                    callback.onFail(dto.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseListStage> call, Throwable t) {
                callback.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void addNewStage(int assetId, Stage newStage, final CallbackData<Stage> callback) {
        Call<ResponseDTO<Stage>> call = stageService.addNewStage(assetId, newStage);
        call.enqueue(new Callback<ResponseDTO<Stage>>() {
            @Override
            public void onResponse(Call<ResponseDTO<Stage>> call, Response<ResponseDTO<Stage>> response) {
                ResponseDTO<Stage> dto = response.body();
                if(dto.getStatus().equals(ResponseStatus.OK.toString())){
                    callback.onSuccess(dto.getPayload());
                }
                else
                    callback.onFail(dto.getStatus());
            }

            @Override
            public void onFailure(Call<ResponseDTO<Stage>> call, Throwable t) {
                    callback.onFail(t.getMessage());
            }
        });
    }
}
