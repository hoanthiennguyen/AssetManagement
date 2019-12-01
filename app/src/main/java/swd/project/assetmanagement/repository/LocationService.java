package swd.project.assetmanagement.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseListBlock;
import swd.project.assetmanagement.dto.ResponseListFloor;
import swd.project.assetmanagement.dto.ResponseListLocation;

public interface LocationService {
    @GET(ConfigApi.GET_ALL_BLOCKS)
    Call<ResponseListBlock> fetchAllBlocks();
    @GET(ConfigApi.GET_ALL_FLOOR)
    Call<ResponseListFloor> fetchAllFloorOfBlock(@Query("block") String block);
    @GET(ConfigApi.GET_ALL_LOCATIONS)
    Call<ResponseListLocation> fetchAllLocationOfBlockAndFloor(@Query("block") String block, @Query("floor") String floor);

}
