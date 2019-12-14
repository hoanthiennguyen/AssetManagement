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

public class NotificationListToMeAdapter extends BaseAdapter {
    List<TransferRequest> notificationList;

    public NotificationListToMeAdapter(List<TransferRequest> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public int getCount() {
        return notificationList.size();
    }

    @Override
    public Object getItem(int position) {
        return notificationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.fragment_item_notification, parent, false);
        }

        TransferRequest transferRequest = notificationList.get(position);
        TextView txtName = view.findViewById(R.id.txtUser);
        TextView txtContent = view.findViewById(R.id.txtContent);
        TextView txtDate = view.findViewById(R.id.txtDate);
        TextView txtStatus = view.findViewById(R.id.txtStatus);

        txtName.setText(transferRequest.getFromEmployee().getFullName());
        txtDate.setText(transferRequest.getCreatedTime());
        if(transferRequest.getStatus().equals("PENDING")) {
            txtContent.setText("has transfer " + transferRequest.getAsset().getName() + " to you.");
        }else if(transferRequest.getStatus().equals("APPROVED") && transferRequest.isSeenByReceiver()) {
            txtContent.setText("You had APPROVED " + transferRequest.getFromEmployee().getFullName() + "'s transfer a " + transferRequest.getAsset().getName() + ".");
            txtStatus.setText("APPROVED");
            txtStatus.setTextColor(Color.parseColor("#46FF00"));
        }else if(transferRequest.getStatus().equals("DENIED") && transferRequest.isSeenByReceiver()) {
            txtContent.setText("You had DENIED " + transferRequest.getFromEmployee().getFullName() + "'s transfer a " + transferRequest.getAsset().getName() + ".");
            txtStatus.setText("DENIED");
            txtStatus.setTextColor(Color.parseColor("#FF0000"));
        }
        return view;
    }
}
