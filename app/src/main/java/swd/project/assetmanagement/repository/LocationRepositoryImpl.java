package swd.project.assetmanagement.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.api_util.RetrofitConfiguration;
import swd.project.assetmanagement.dto.ResponseListBlock;
import swd.project.assetmanagement.dto.ResponseListFloor;
import swd.project.assetmanagement.dto.ResponseListLocation;
import swd.project.assetmanagement.dto.ResponseStatus;
import swd.project.assetmanagement.model.Location;

public class LocationRepositoryImpl implements LocationRepository{
    LocationService service = RetrofitConfiguration.getRetrofitAdapter().create(LocationService.class);
    @Override
    public void fetchAllBlocks(final CallbackData<List<String>> callbackData) {
        Call<ResponseListBlock> call = service.fetchAllBlocks();
        call.enqueue(new Callback<ResponseListBlock>() {
            @Override
            public void onResponse(Call<ResponseListBlock> call, Response<ResponseListBlock> response) {
                ResponseListBlock dto = response.body();
                if(dto != null && dto.getStatus().equals(ResponseStatus.OK.toString())){
                    callbackData.onSuccess(dto.getPayload());
                }
                else if(dto != null)
                    callbackData.onFail(dto.getStatus());
                else
                    callbackData.onFail("Internal Server Error");
            }

            @Override
            public void onFailure(Call<ResponseListBlock> call, Throwable t) {
                    callbackData.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void fetchAllFloorOfBlock(String block,final CallbackData<List<String>> callbackData) {
        Call<ResponseListFloor> call = service.fetchAllFloorOfBlock(block);
        call.enqueue(new Callback<ResponseListFloor>() {
            @Override
            public void onResponse(Call<ResponseListFloor> call, Response<ResponseListFloor> response) {
                ResponseListFloor dto = response.body();
                if(dto != null && dto.getStatus().equals(ResponseStatus.OK.toString())){
                    callbackData.onSuccess(dto.getPayload());
                }
                else if(dto != null)
                    callbackData.onFail(dto.getStatus());
                else
                    callbackData.onFail("Internal Server Error");
            }

            @Override
            public void onFailure(Call<ResponseListFloor> call, Throwable t) {
                    callbackData.onFail(t.getMessage());
            }
        });

    }

    @Override
    public void fetchAllLocationOfBlockAndFloor(String block, String floor,final CallbackData<List<Location>> callbackData) {
        Call<ResponseListLocation> call = service.fetchAllLocationOfBlockAndFloor(block,floor);
        call.enqueue(new Callback<ResponseListLocation>() {
            @Override
            public void onResponse(Call<ResponseListLocation> call, Response<ResponseListLocation> response) {
                ResponseListLocation dto = response.body();
                if(dto != null && dto.getStatus().equals(ResponseStatus.OK.toString())){
                    callbackData.onSuccess(dto.getPayload());
                }
                else if(dto != null)
                    callbackData.onFail(dto.getStatus());
                else
                    callbackData.onFail("Internal Server Error");
            }

            @Override
            public void onFailure(Call<ResponseListLocation> call, Throwable t) {
                    callbackData.onFail(t.getMessage());
            }
        });
    }
}
