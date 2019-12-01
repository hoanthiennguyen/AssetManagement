package swd.project.assetmanagement.repository;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.TransferRequest;

public interface TransferRequestRepository {
    void fetchTransferRequestToMe(int employeeId, CallbackData<List<TransferRequest>> callback);
    void fetchTransferRequestFromMe(int employeeId, CallbackData<List<TransferRequest>> callback);
}
