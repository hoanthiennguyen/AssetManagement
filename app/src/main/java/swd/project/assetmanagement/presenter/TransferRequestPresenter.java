package swd.project.assetmanagement.presenter;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.TransferRequest;
import swd.project.assetmanagement.repository.TransferRequestRepository;
import swd.project.assetmanagement.repository.TransferRequestRepositoryImpl;
import swd.project.assetmanagement.view.TransferRequestListView;

public class TransferRequestPresenter {
    TransferRequestListView requestListView;
    TransferRequestRepository repository = new TransferRequestRepositoryImpl();

    public TransferRequestPresenter(TransferRequestListView requestListView) {
        this.requestListView = requestListView;
    }
    public void fetchTransferRequestToMe(int employeeId){
        repository.fetchTransferRequestToMe(employeeId, new CallbackData<List<TransferRequest>>() {
            @Override
            public void onSuccess(List<TransferRequest> list) {
                requestListView.onSuccessGetTransferRequestsToMe(list);
            }

            @Override
            public void onFail(String msg) {
                requestListView.onFailGetTransferRequestToMe(msg);
            }
        });
    }
    public void fetchTransferRequestFromMe(int employeeId){
        repository.fetchTransferRequestFromMe(employeeId, new CallbackData<List<TransferRequest>>() {
            @Override
            public void onSuccess(List<TransferRequest> list) {
                requestListView.onSuccessGetTransferRequestsFromMe(list);
            }

            @Override
            public void onFail(String msg) {
                requestListView.onFailGetTransferRequestFromMe(msg);
            }
        });
    }
}
