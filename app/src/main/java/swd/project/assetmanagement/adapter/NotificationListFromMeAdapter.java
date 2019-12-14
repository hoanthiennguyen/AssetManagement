package swd.project.assetmanagement.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import swd.project.assetmanagement.R;
import swd.project.assetmanagement.model.TransferRequest;

public class NotificationListFromMeAdapter extends BaseAdapter {
    List<TransferRequest> notificationList;

    public NotificationListFromMeAdapter(List<TransferRequest> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int i) {
        return notificationList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.fragment_item_notification, viewGroup, false);
        }
        TransferRequest transferRequest = notificationList.get(i);
        TextView txtName = view.findViewById(R.id.txtUser);
        TextView txtContent = view.findViewById(R.id.txtContent);
        TextView txtDate = view.findViewById(R.id.txtDate);
        TextView txtStatus = view.findViewById(R.id.txtStatus);

        txtName.setText(transferRequest.getToEmployee().getFullName());
        txtDate.setText(transferRequest.getCreatedTime());
        txtDate.setText(transferRequest.getCreatedTime());
        if(transferRequest.getStatus().equals("PENDING")) {
            txtContent.setText("Your has transfer "+transferRequest.getAsset().getName() +" to " + transferRequest.getToEmployee().getFullName() );
        }else if(transferRequest.getStatus().equals("APPROVED") && transferRequest.isSeenByReceiver()) {
            txtContent.setText(transferRequest.getToEmployee().getFullName() + " has accept your transfer.");
            txtStatus.setText("APPROVED");
            txtStatus.setTextColor(Color.parseColor("#46FF00"));
        }else if(transferRequest.getStatus().equals("DENIED") && transferRequest.isSeenByReceiver()) {
            txtContent.setText(transferRequest.getToEmployee().getFullName() + " has deny your transfer.");
            txtStatus.setText("DENIED");
            txtStatus.setTextColor(Color.parseColor("#FF0000"));
        }
        return view;
    }
}
