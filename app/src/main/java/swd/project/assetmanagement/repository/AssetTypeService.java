package swd.project.assetmanagement.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseListAssetType;

public interface AssetTypeService {
    @GET(ConfigApi.GET_ASSET_TYPE)
    Call<ResponseListAssetType> getAllAssetType();
}
