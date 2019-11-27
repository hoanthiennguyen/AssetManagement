package swd.project.assetmanagement;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swd.project.assetmanagement.adapter.AssetListViewAdapter;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.presenter.AssetListPresenter;
import swd.project.assetmanagement.view.AssetListView;
import swd.project.assetmanagement.view.LoadingView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements AssetListView, LoadingView {

    ListView listViewAsset;
    AssetListViewAdapter assetListViewAdapter;
    List<Asset> assetList;
    AssetListPresenter assetListPresenter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listViewAsset = view.findViewById(R.id.assetContainer);
        assetListPresenter = new AssetListPresenter(this,this);
        assetList = new ArrayList<>();
        assetListViewAdapter = new AssetListViewAdapter(assetList);
        listViewAsset.setAdapter(assetListViewAdapter);
        assetListPresenter.fetchListAssetFromServer();
        listViewAsset.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Asset asset = assetList.get(position);
                Intent intent = new Intent(getActivity(),AssetDetailsActivity.class);
                intent.putExtra("asset",asset);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onSuccessFetchAssetList(List<Asset> movieArrayList) {
        assetList.addAll(movieArrayList);
        assetListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureFetchAssetList(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
