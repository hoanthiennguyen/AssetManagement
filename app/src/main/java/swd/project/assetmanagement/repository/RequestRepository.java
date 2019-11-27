package swd.project.assetmanagement.repository;

import android.app.Notification;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;

public interface RequestRepository {
    void fetchNotification(CallbackData<List<Notification>> callback);
}
