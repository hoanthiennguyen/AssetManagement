package swd.project.assetmanagement.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseDTO;
import swd.project.assetmanagement.dto.ResponseListAsset;
import swd.project.assetmanagement.model.Asset;

public interface AssetService {
    @GET(ConfigApi.GET_ALL_ASSETS)
    Call<ResponseListAsset> getAllAsset();
    @PUT(ConfigApi.EDIT_ASSET)
    Call<ResponseDTO> updateAsset(Asset asset);
    @GET(ConfigApi.GET_ASSET)
    Call<ResponseDTO<Asset>> getAsset(@Path("assetId") int assetId);

}
