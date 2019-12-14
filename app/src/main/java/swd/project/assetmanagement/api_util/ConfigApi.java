package swd.project.assetmanagement.api_util;

public interface ConfigApi {
    String BASE_URL = "https://asset-management-system-api.herokuapp.com/api/v1/";

    String LOGIN_GG = "google-authentication";

    String EDIT_ASSET = "firms/1/assets";
    String GET_ALL_ASSETS = "firms/1/assets";
    String GET_ASSET = "firms/1/assets/{assetId}";
    String GET_ASSET_STAGES = "firms/1/assets/{assetId}/stages";
    String CHANGE_ASSET_STAGE = "firms/1/assets/{assetId}/stages";
    String GET_NOTIFICATIONS = "firms/1/transfer-request";
    String GET_ALL_BLOCKS = "firms/1/blocks";
    String GET_ALL_FLOOR = "firms/1/floors";
    String GET_ALL_LOCATIONS = "firms/1/locations";
    String GET_ASSET_TYPE = "firms/1/asset-types";
    String GET_EMPLOYEE = "firms/1/employees";
    String MAKE_REQUEST = "firms/1/transfer-request";
    String UPDATE_REQUEST = "firms/1/transfer-request/{requestId}";
}
