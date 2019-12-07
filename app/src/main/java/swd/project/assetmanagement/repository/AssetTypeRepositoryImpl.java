package swd.project.assetmanagement.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.api_util.RetrofitConfiguration;
import swd.project.assetmanagement.dto.ResponseListAssetType;
import swd.project.assetmanagement.dto.ResponseStatus;
import swd.project.assetmanagement.model.AssetType;

public class AssetTypeRepositoryImpl implements AssetTypeRepository {
    AssetTypeService service = RetrofitConfiguration.getRetrofitAdapter().create(AssetTypeService.class);

    @Override
    public void fetchListAssetType(final CallbackData<List<AssetType>> callBack) {
        Call<ResponseListAssetType> call = service.getAllAssetType();
        call.enqueue(new Callback<ResponseListAssetType>() {
            @Override
            public void onResponse(Call<ResponseListAssetType> call, Response<ResponseListAssetType> response) {
                ResponseListAssetType dto = response.body();
                if (dto != null && dto.getStatus().equals(ResponseStatus.OK.toString())) {
                    List<AssetType> list = dto.getPayload();
                    callBack.onSuccess(list);
                } else {
                    if (dto != null)
                        callBack.onFail(dto.getStatus());
                    else
                        callBack.onFail("Internal server error");
                }
            }

            @Override
            public void onFailure(Call<ResponseListAssetType> call, Throwable t) {

            }
        });
    }
}
