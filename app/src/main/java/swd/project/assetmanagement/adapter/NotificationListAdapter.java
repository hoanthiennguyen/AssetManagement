package swd.project.assetmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import swd.project.assetmanagement.R;
import swd.project.assetmanagement.model.TransferRequest;

public class NotificationListAdapter extends BaseAdapter {
    List<TransferRequest> notificationList;

    public NotificationListAdapter(List<TransferRequest> notificationList) {
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
        String sender = transferRequest.getFromEmployee().getFullname();
        String receiver = transferRequest.getToEmployee().getFullname();
        txtName.setText(receiver);
        txtContent.setText("Has send a request!");
        txtDate.setText(transferRequest.getCreatedTime());
        return view;
    }

    /*public boolean isSender(String username, String sender, int position) {
        if(username.equals(sender)) {
            return true;
        }
        return false;
    }*/
}
