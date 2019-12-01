package swd.project.assetmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import swd.project.assetmanagement.R;
import swd.project.assetmanagement.model.Location;

public class LocationListAdapter extends BaseAdapter {
    List<Location> locationList;

    public LocationListAdapter(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int position) {
        return locationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return locationList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_item_location, parent, false);
        }
        TextView txtRoom = convertView.findViewById(R.id.txtRoom);
        txtRoom.setText(locationList.get(position).getRoom());
        return convertView;
    }
}
