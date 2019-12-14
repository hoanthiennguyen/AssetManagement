package swd.project.assetmanagement.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import swd.project.assetmanagement.R;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.model.Stage;

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
        return assetList.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.fragment_item_asset,parent,false);

        }
        final Asset asset = assetList.get(position);
        ImageView assetImage = view.findViewById(R.id.assetImage);
        TextView assetName = view.findViewById(R.id.assetName);
        TextView assetType = view.findViewById(R.id.assetType);
        TextView assetLocation = view.findViewById(R.id.assetLocation);
        TextView assetStatus = view.findViewById(R.id.assetStatus);
        assetType.setText(asset.getAssetType() != null ? asset.getAssetType().getName() : "Unassigned");
        assetName.setText(asset.getName());
        Stage currentStage = asset.getCurrentStage();
        assetImage.setImageResource(R.drawable.ic_tv_black_24dp);
        assetLocation.setText(currentStage.getLocation() != null ? currentStage.getLocation().toString() : "Unassigned");
        if(currentStage.getStatus().equals("CREATED")) {
            assetStatus.setText("STABLE");
            assetStatus.setTextColor(Color.parseColor("#46FF00"));
        }else if(currentStage.getStatus().equals("STABLE")) {
            assetStatus.setText("STABLE");
            assetStatus.setTextColor(Color.parseColor("#46FF00"));
        }else if(currentStage.getStatus().equals("DAMAGED")) {
            assetStatus.setText("DAMAGED");
            assetStatus.setTextColor(Color.parseColor("#FF0000"));
        }else if(currentStage.getStatus().equals("EXPIRED")) {
            assetStatus.setText("EXPIRED");
            assetStatus.setTextColor(Color.parseColor("#9B00FF"));
        }else if(currentStage.getStatus().equals("REMOVED")) {
            assetStatus.setText("REMOVED");
            assetStatus.setTextColor(Color.parseColor("#9E9E9E"));
        }else if(currentStage.getStatus().equals("REPAIRING")) {
            assetStatus.setText("REPAIRING");
            assetStatus.setTextColor(Color.parseColor("#F4D03F"));
        }
        return view;
    }
}
