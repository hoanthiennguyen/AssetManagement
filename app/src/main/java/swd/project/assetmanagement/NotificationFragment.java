package swd.project.assetmanagement;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import swd.project.assetmanagement.adapter.NotificationListFromMeAdapter;
import swd.project.assetmanagement.adapter.NotificationListToMeAdapter;
import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.LoginDTO;
import swd.project.assetmanagement.model.Stage;
import swd.project.assetmanagement.model.TransferRequest;
import swd.project.assetmanagement.presenter.TransferRequestPresenter;
import swd.project.assetmanagement.repository.TransferRequestRepositoryImpl;
import swd.project.assetmanagement.view.TransferRequestListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment implements TransferRequestListView {
    ListView listViewTransferToMe;
    ListView listViewTransferFromMe;
    List<TransferRequest> listToMe;
    List<TransferRequest> listFromMe;
    TransferRequestPresenter requestPresenter;
    private NotificationListToMeAdapter adapterToMe;
    private NotificationListFromMeAdapter adapterFromMe;
    LoginDTO employee;
    TransferRequestRepositoryImpl transferRepo = new TransferRequestRepositoryImpl();


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        MainActivity mainActivity = (MainActivity) view.getContext();
        employee = mainActivity.employee;

        listViewTransferToMe = view.findViewById(R.id.notificationToMeContainer);
        listViewTransferFromMe = view.findViewById(R.id.notificationFromMeContainer);
        listToMe = new ArrayList<>();
        listFromMe = new ArrayList<>();
        adapterToMe = new NotificationListToMeAdapter(listToMe);
        adapterFromMe = new NotificationListFromMeAdapter(listFromMe);
        listViewTransferToMe.setAdapter(adapterToMe);
        listViewTransferFromMe.setAdapter(adapterFromMe);
        requestPresenter = new TransferRequestPresenter(this);
        requestPresenter.fetchTransferRequestToMe(employee.getEmployee().getId());
        requestPresenter.fetchTransferRequestFromMe(employee.getEmployee().getId());

        listViewTransferToMe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final TransferRequest request = listToMe.get(i);
                final Stage stage = request.getAsset().getCurrentStage();
                if(request.getStatus().equals("PENDING")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage(request.getFromEmployee().getFullName() + " transfer " + request.getAsset().getName() + " for you.");
                    builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            request.setStatus("APPROVED");
                            request.setSeenByReceiver(true);
                            updateTransfer(request.getId(), request, "APPROVED");
                        }
                    });
                    builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            request.setStatus("DENIED");
                            request.setSeenByReceiver(true);
                            updateTransfer(request.getId(), request, "DENIED");
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
        return view;
    }

    @Override
    public void onSuccessGetTransferRequestsToMe(List<TransferRequest> list) {
        listToMe.clear();
        listToMe.addAll(list);
        adapterToMe.notifyDataSetChanged();
    }

    @Override
    public void onFailGetTransferRequestToMe(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessGetTransferRequestsFromMe(List<TransferRequest> list) {
        listFromMe.clear();
        listFromMe.addAll(list);
        adapterFromMe.notifyDataSetChanged();
    }

    @Override
    public void onFailGetTransferRequestFromMe(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    public void updateTransfer(Long id, TransferRequest transferRequest, final String status) {
        transferRepo.editTransfer(id, transferRequest, new CallbackData<TransferRequest>() {
            @Override
            public void onSuccess(TransferRequest transferRequest) {
                if(transferRequest != null) {
                    if(transferRequest.getStatus().equals("DENIED")) {
                        Toast.makeText(getContext(), status + " success!!!", Toast.LENGTH_LONG).show();
                    }
                }else  {
                    Toast.makeText(getContext(), status + " fail!!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFail(String msg) {
//                Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
