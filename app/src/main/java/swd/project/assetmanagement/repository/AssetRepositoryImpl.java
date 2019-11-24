package swd.project.assetmanagement.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swd.project.assetmanagement.api_util.CallBackData;
import swd.project.assetmanagement.api_util.ResponseDTO;
import swd.project.assetmanagement.api_util.ResponseListAsset;
import swd.project.assetmanagement.api_util.RetrofitConfiguration;
import swd.project.assetmanagement.model.Asset;

public class AssetRepositoryImpl implements AssetRepository{
    @Override
    public void getAllAsset(final CallBackData<List<Asset>> callBack) {

        Call<ResponseListAsset> call = RetrofitConfiguration.getRetrofitAdapter().create(AssetService.class).getAllAsset();
        call.enqueue(new Callback<ResponseListAsset>() {
            @Override
            public void onResponse(Call<ResponseListAsset> call, Response<ResponseListAsset> response) {
                try{
                    ResponseDTO<List<Asset>> responseDTO = response.body();
                    if(responseDTO.getStatus().equals("OK")){
                        List<Asset> list = response.body().getPayload();
                        callBack.onSuccess(list);

                    }
                }
                catch (Exception e){
                    callBack.onFail(e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponseListAsset> call, Throwable t) {
                    callBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void updateAsset(CallBackData<Asset> callBack) {

    }
}
