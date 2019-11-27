package swd.project.assetmanagement.repository;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseDTO;
import swd.project.assetmanagement.dto.ResponseListStage;
import swd.project.assetmanagement.model.Stage;

public interface StageService {
    @GET(ConfigApi.GET_ASSET_STAGES)
    Call<ResponseListStage> fetchAssetStages(@Path("assetId") int assetId);
    @POST(ConfigApi.CHANGE_ASSET_STAGE)
    Call<ResponseDTO<Stage>> addNewStage(@Path("assetId") int assetId, @Body Stage stage);
}
