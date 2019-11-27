package swd.project.assetmanagement.api_util;

public interface ConfigApi {
    String BASE_URL = "https://asset-management-system-api.herokuapp.com/api/v1/firms/1/departments/1/";

    String LOGIN = "authen/login";
    String REGISTER = "authen/signup";
    String EDIT_ASSET = "assets";
    String GET_ALL_ASSETS = "assets";
    String GET_ASSET = "assets/{assetId}";
    String GET_ASSET_STAGES = "assets/{assetId}/stages";
    String CHANGE_ASSET_STAGE = "assets/{assetId}/stages";
    String GET_NOTIFICATIONS = "notifications";

}
