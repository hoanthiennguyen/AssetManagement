package swd.project.assetmanagement.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseListTransferRequest;

public interface TransferRequestService {
    @GET(ConfigApi.GET_NOTIFICATIONS)
    Call<ResponseListTransferRequest> getListTransferRequestToMe(@Query("employeeIdParaTo") int employeeIdParaTo);
    @GET(ConfigApi.GET_NOTIFICATIONS)
    Call<ResponseListTransferRequest> getListTransferRequestFromMe(@Query("employeeIdParaFrom") int employeeIdParaFrom);
}
