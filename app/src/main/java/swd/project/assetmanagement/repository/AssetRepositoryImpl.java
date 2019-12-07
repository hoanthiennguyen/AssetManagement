package swd.project.assetmanagement.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.dto.ResponseDTO;
import swd.project.assetmanagement.dto.ResponseListAsset;
import swd.project.assetmanagement.api_util.RetrofitConfiguration;
import swd.project.assetmanagement.dto.ResponseStatus;
import swd.project.assetmanagement.model.Asset;

public class AssetRepositoryImpl implements AssetRepository{
    AssetService assetService = RetrofitConfiguration.getRetrofitAdapter().create(AssetService.class);
    @Override
    public void fetchListAsset(final CallbackData<List<Asset>> callBack) {

        Call<ResponseListAsset> call = assetService.getAllAsset();
        call.enqueue(new Callback<ResponseListAsset>() {
            @Override
            public void onResponse(Call<ResponseListAsset> call, Response<ResponseListAsset> response) {
                    ResponseListAsset dto = response.body();
                    if(dto != null && dto.getStatus().equals(ResponseStatus.OK.toString())){
                    List<Asset> list = dto.getPayload();
                    callBack.onSuccess(list);
                }
                else{
                    if(dto != null)
                        callBack.onFail(dto.getStatus());
                    else
                        callBack.onFail("Internal server error");
                }

            }

            @Override
            public void onFailure(Call<ResponseListAsset> call, Throwable t) {
                    callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void updateAsset(CallbackData<Asset> callBack) {

    }

    @Override
    public void fetchAssetDetails(int assetId, final CallbackData<Asset> callback) {
        Call<ResponseDTO<Asset>> call = assetService.getAsset(assetId);
        call.enqueue(new Callback<ResponseDTO<Asset>>() {
            @Override
            public void onResponse(Call<ResponseDTO<Asset>> call, Response<ResponseDTO<Asset>> response) {
                ResponseDTO<Asset> dto = response.body();
                if(dto.getStatus().equals(ResponseStatus.OK.toString())){
                    Asset asset = dto.getPayload();
                    callback.onSuccess(asset);
                }
                else {
                    callback.onFail(dto.getStatus());
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<Asset>> call, Throwable t) {

            }
        });
    }

    @Override
    public void fiterAsset(String room, String status, Long assetTypeId, final CallbackData<List<Asset>> callBack) {
        Call<ResponseListAsset> call = assetService.filterAsset(room, status, assetTypeId);
        call.enqueue(new Callback<ResponseListAsset>() {
            @Override
            public void onResponse(Call<ResponseListAsset> call, Response<ResponseListAsset> response) {
                ResponseListAsset dto = response.body();
                if(dto != null && dto.getStatus().equals(ResponseStatus.OK.toString())){
                    List<Asset> list = dto.getPayload();
                    callBack.onSuccess(list);
                }
                else{
                    if(dto != null)
                        callBack.onFail(dto.getStatus());
                    else
                        callBack.onFail("Internal server error");
                }
            }

            @Override
            public void onFailure(Call<ResponseListAsset> call, Throwable t) {
                callBack.onFail(t.getMessage());
            }
        });
    }
}
