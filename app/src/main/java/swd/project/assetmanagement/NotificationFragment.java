package swd.project.assetmanagement;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swd.project.assetmanagement.adapter.NotificationListAdapter;
import swd.project.assetmanagement.model.TransferRequest;
import swd.project.assetmanagement.presenter.TransferRequestPresenter;
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
    private NotificationListAdapter adapterToMe;
    private NotificationListAdapter adapterFromMe;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        listViewTransferToMe = view.findViewById(R.id.notificationToMeContainer);
        listViewTransferFromMe = view.findViewById(R.id.notificationFromMeContainer);
        listToMe = new ArrayList<>();
        listFromMe = new ArrayList<>();
        adapterToMe = new NotificationListAdapter(listToMe);
        adapterFromMe = new NotificationListAdapter(listFromMe);
        listViewTransferToMe.setAdapter(adapterToMe);
        listViewTransferFromMe.setAdapter(adapterFromMe);
        requestPresenter = new TransferRequestPresenter(this);
        requestPresenter.fetchTransferRequestToMe(1);
        requestPresenter.fetchTransferRequestFromMe(1);
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
}
