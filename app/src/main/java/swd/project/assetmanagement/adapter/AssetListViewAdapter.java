package swd.project.assetmanagement.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import swd.project.assetmanagement.R;
import swd.project.assetmanagement.model.Asset;

public class AssetListViewAdapter extends BaseAdapter {
    List<Asset> assetList;

    public AssetListViewAdapter(List<Asset> assetList) {
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
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.fragment_item_asset,parent,false);

        }
        Asset asset = assetList.get(position);
        ImageView assetImage = view.findViewById(R.id.assetImage);
        TextView assetname = view.findViewById(R.id.assetName);
        TextView assetType = view.findViewById(R.id.assetType);
        TextView assetLocation = view.findViewById(R.id.assetLocation);
        Button assetStatus = view.findViewById(R.id.assetStatus);
        String status = asset.getStatus();
        assetImage.setImageResource(R.drawable.ic_tv_black_24dp);
        assetname.setText(asset.getName());
        assetType.setText(asset.getAssetType().getName());
        assetLocation.setText("111");
        assetStatus.setText(asset.getStatus());
        if(status == "STABLE") {
            assetStatus.setBackgroundColor(Color.GREEN);
        }else if(status == "PREPAIRING") {
            assetStatus.setBackgroundColor(Color.YELLOW);
        }else if(status == "DAMAGED") {
            assetStatus.setBackgroundColor(Color.RED);
        }else if(status == "REMOVE") {
            assetStatus.setBackgroundColor(Color.GRAY);
        }else if(status == "EXPIRED") {
            assetStatus.setBackgroundColor(Color.BLACK);
        }

        return view;
    }
}
