package swd.project.assetmanagement.api_util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import swd.project.assetmanagement.model.Asset;

public interface ApiInterface {
    @GET("movie/popular")
    Call<List<Asset>> getAllAsset();

}
