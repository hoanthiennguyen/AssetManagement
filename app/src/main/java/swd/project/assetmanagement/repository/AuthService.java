package swd.project.assetmanagement.repository;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import swd.project.assetmanagement.api_util.ConfigApi;
import swd.project.assetmanagement.dto.ResponseLogin;

public interface AuthService {
    @POST(ConfigApi.LOGIN_GG)
    Call<ResponseLogin> checkLoginByGG(@Body RequestBody body);
}
