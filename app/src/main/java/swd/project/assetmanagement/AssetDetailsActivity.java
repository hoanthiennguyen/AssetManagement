package swd.project.assetmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.TypedArrayUtils;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import swd.project.assetmanagement.adapter.StageListViewAdapter;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.model.AssetStatus;
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
    String[] items = AssetStatus.allValues;
    Stage currentStage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_details);

        assetDetailsPresenter = new AssetDetailsPresenter(this,this);
        stageListPresenter = new StageListPresenter(this);
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
        spinnerChangeStatus = findViewById(R.id.spinnerChangeStatus);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinnerChangeStatus.setAdapter(adapter);
        ImageView assetImage = findViewById(R.id.assetImage);
        TextView assetName = findViewById(R.id.assetName);
        TextView assetType = findViewById(R.id.assetType);
        TextView assetLocation = findViewById(R.id.assetLocation);
        currentStage = asset.getCurrentStage();
        assetImage.setImageResource(R.drawable.ic_tv_black_24dp);
        int position = Arrays.asList(items).indexOf(currentStage.getStatus());
        spinnerChangeStatus.setSelection(position);
        assetName.setText(asset.getName());
        assetLocation.setText(currentStage.getLocation() != null ? currentStage.getLocation().toString(): "Unknown");
        assetType.setText(asset.getAssetType() != null ? asset.getAssetType().getName() : "Unknown");
        spinnerChangeStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentStage.setStatus(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stageList = new ArrayList<>();
        stageListView = findViewById(R.id.stageListView);
        stageListViewAdapter = new StageListViewAdapter(stageList);
        stageListView.setAdapter(stageListViewAdapter);
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
