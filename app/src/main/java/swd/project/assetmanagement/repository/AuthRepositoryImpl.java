package swd.project.assetmanagement.repository;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.api_util.RetrofitConfiguration;
import swd.project.assetmanagement.dto.ResponseLogin;
import swd.project.assetmanagement.dto.ResponseStatus;
import swd.project.assetmanagement.model.LoginDTO;

public class AuthRepositoryImpl implements AuthRepository{
    AuthService service = RetrofitConfiguration.getRetrofitAdapter().create(AuthService.class);

    @Override
    public void loginByGG(String googleToken, final CallbackData<LoginDTO> callBack) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), googleToken);
        Call<ResponseLogin> call = service.checkLoginByGG(body);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                ResponseLogin dto = response.body();
                if(dto != null && dto.getStatus().equals(ResponseStatus.OK.toString())) {
                    LoginDTO user = dto.getPayload();
                    callBack.onSuccess(user);
                }else {
                    if(dto != null)
                        callBack.onFail(dto.getStatus());
                    else
                        callBack.onFail("Internal server error");
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                if(t != null) {
                    callBack.onFail(t.getMessage());
                }
            }
        });
    }
}
