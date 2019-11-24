package swd.project.assetmanagement.api_util;

public interface CallBackData<T> {
    void onSuccess(T t);
    void onFail(String t);
}
