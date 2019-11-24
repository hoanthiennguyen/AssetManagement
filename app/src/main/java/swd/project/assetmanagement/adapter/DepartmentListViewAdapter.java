package swd.project.assetmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import swd.project.assetmanagement.R;
import swd.project.assetmanagement.model.Asset;

public class DepartmentListViewAdapter extends BaseAdapter {
    List<Asset> assetList;

    public DepartmentListViewAdapter(List<Asset> assetList) {
        this.assetList = assetList;
    }

    @Override
    public int getCount() {
        return assetList.size();
    }

    @Override
    public Object getItem(int position) {
        return assetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_asset_item,parent,false);

        }
        Asset asset = assetList.get(position);
        TextView txtDepartmentName = convertView.findViewById(R.id.txtAssetName);
        txtDepartmentName.setText(asset.getName());
        return convertView;
    }
}
