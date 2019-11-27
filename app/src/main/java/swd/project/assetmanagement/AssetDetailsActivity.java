package swd.project.assetmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swd.project.assetmanagement.adapter.StageListViewAdapter;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.model.Stage;
import swd.project.assetmanagement.presenter.AssetDetailsPresenter;
import swd.project.assetmanagement.presenter.StageListPresenter;
import swd.project.assetmanagement.view.AssetDetailsView;
import swd.project.assetmanagement.view.LoadingView;
import swd.project.assetmanagement.view.StageListView;

public class AssetDetailsActivity extends AppCompatActivity implements LoadingView, AssetDetailsView, StageListView {
    AssetDetailsPresenter assetDetailsPresenter;
    Asset asset;
    List<Stage> stageList;
    ListView stageListView;
    StageListViewAdapter stageListViewAdapter;
    StageListPresenter stageListPresenter;
    Spinner spinnerChangeStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_details);
        stageList = new ArrayList<>();
        assetDetailsPresenter = new AssetDetailsPresenter(this,this);
        stageListPresenter = new StageListPresenter(this);

        spinnerChangeStatus = findViewById(R.id.spinnerChangeStatus);
        String[] items = new String[]{"1", "2", "three"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerChangeStatus.setAdapter(adapter);
        stageListView = findViewById(R.id.stageListView);
        stageListViewAdapter = new StageListViewAdapter(stageList);
        stageListView.setAdapter(stageListViewAdapter);
        Asset asset =(Asset) getIntent().getSerializableExtra("asset");
        if(asset != null){
            this.asset = asset;
            renderView();
        }
        else {
            int id = getIntent().getIntExtra("assetId",0);
            assetDetailsPresenter.fetchAssetDetails(id);
        }
    }
    private void renderView(){
        ImageView assetImage = findViewById(R.id.assetImage);
        TextView assetName = findViewById(R.id.assetName);
        TextView assetType = findViewById(R.id.assetType);
        TextView assetLocation = findViewById(R.id.assetLocation);
        TextView assetStatus = findViewById(R.id.assetStatus);
        Stage currentStage = asset.getCurrentStage();
        assetImage.setImageResource(R.drawable.ic_tv_black_24dp);
        assetStatus.setText(currentStage.getStatus());
        assetName.setText(asset.getName());
        assetLocation.setText(currentStage.getLocation() != null ? currentStage.getLocation().toString(): "Unknown");
        assetType.setText(asset.getAssetType() != null ? asset.getAssetType().getName() : "Unknown");
        stageListPresenter.fetchListStage(asset.getId());
    }

    @Override
    public void getAssetDetailsSuccess(Asset asset) {
        this.asset = asset;
        renderView();
    }

    @Override
    public void getAssetDetailsFail(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
    public void onClickToggleHistory(View view){

        if(stageListView.getVisibility() == View.GONE){
            stageListView.setVisibility(View.VISIBLE);
            Button btn = (Button) view;
            btn.setText("Hide History");
        }
        else {
            stageListView.setVisibility(View.GONE);
            Button btn = (Button) view;
            btn.setText("Show History");
        }
    }

    @Override
    public void onSuccessFetchStageList(List<Stage> stages) {
        stageList.addAll(stages);
        stageListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailFetchStageList(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
