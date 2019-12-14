package swd.project.assetmanagement.repository;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.LoginDTO;

public interface AuthRepository {
    void loginByGG(String googleToken, CallbackData<LoginDTO> callBack);
}
