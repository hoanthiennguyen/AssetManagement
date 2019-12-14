package swd.project.assetmanagement.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.api_util.RetrofitConfiguration;
import swd.project.assetmanagement.dto.ResponseListEmployee;
import swd.project.assetmanagement.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    EmployeeService service = RetrofitConfiguration.getRetrofitAdapter().create(EmployeeService.class);

    @Override
    public void fetchEmployee(long departmentId, final CallbackData<List<Employee>> callBack) {
        Call<ResponseListEmployee> call = service.getEmployee(departmentId);
        call.enqueue(new Callback<ResponseListEmployee>() {
            @Override
            public void onResponse(Call<ResponseListEmployee> call, Response<ResponseListEmployee> response) {
                ResponseListEmployee dto = response.body();
                if(dto != null) {
                    List<Employee> list = dto.getPayload();
                    callBack.onSuccess(list);
                }else {
                    callBack.onFail("Get Employee Error!!!");
                }
            }

            @Override
            public void onFailure(Call<ResponseListEmployee> call, Throwable t) {
                if(t != null) {
                    callBack.onFail(t.getMessage());
                }
            }
        });
    }
}
