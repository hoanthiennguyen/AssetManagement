package swd.project.assetmanagement.view;

import java.util.List;

import swd.project.assetmanagement.model.TransferRequest;

public interface TransferRequestListView {
    void onSuccessGetTransferRequestsToMe(List<TransferRequest> list);
    void onFailGetTransferRequestToMe(String msg);
    void onSuccessGetTransferRequestsFromMe(List<TransferRequest> list);
    void onFailGetTransferRequestFromMe(String msg);
}
