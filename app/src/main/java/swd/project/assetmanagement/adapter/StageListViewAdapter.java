package swd.project.assetmanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import swd.project.assetmanagement.R;
import swd.project.assetmanagement.model.Stage;

public class StageListViewAdapter extends BaseAdapter {
    List<Stage> stageList;

    public StageListViewAdapter(List<Stage> stageList) {
        this.stageList = stageList;
    }

    @Override
    public int getCount() {
        return stageList.size();
    }

    @Override
    public Object getItem(int position) {
        return stageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_item_stage, parent,false);
        }
        TextView txtFromDate = convertView.findViewById(R.id.txtFromDate);
        TextView txtToDate = convertView.findViewById(R.id.txtToDate);
        TextView txtStatus = convertView.findViewById(R.id.txtStatus);
        Stage stage = stageList.get(position);
        txtFromDate.setText(stage.getFromDate());
        txtToDate.setText(stage.getToDate());
        txtStatus.setText(stage.getStatus());
        return convertView;
    }
}
