package swd.project.assetmanagement.repository;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseListEmployee;

public interface EmployeeService {
    @GET(ConfigApi.GET_EMPLOYEE)
    Call<ResponseListEmployee> getEmployee(@Query("departmentId") long departmentId);
}
