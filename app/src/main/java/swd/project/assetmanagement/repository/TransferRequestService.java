package swd.project.assetmanagement.repository;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseListTransferRequest;
import swd.project.assetmanagement.dto.ResponseTransferRequest;
import swd.project.assetmanagement.model.TransferRequest;

public interface TransferRequestService {
    @GET(ConfigApi.GET_NOTIFICATIONS)
    Call<ResponseListTransferRequest> getListTransferRequestToMe(@Query("employeeIdParaTo") int employeeIdParaTo);
    @GET(ConfigApi.GET_NOTIFICATIONS)
    Call<ResponseListTransferRequest> getListTransferRequestFromMe(@Query("employeeIdParaFrom") int employeeIdParaFrom);
    @POST(ConfigApi.MAKE_REQUEST)
    Call<ResponseTransferRequest> makeTrasnfer(@Body TransferRequest transferRequest);
    @PUT(ConfigApi.UPDATE_REQUEST)
    Call<ResponseTransferRequest> updateTranfer(@Path("requestId") long requestId, @Body TransferRequest transferRequest);
}
