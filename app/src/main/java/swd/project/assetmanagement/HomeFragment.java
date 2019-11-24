package swd.project.assetmanagement;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swd.project.assetmanagement.adapter.DepartmentListViewAdapter;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.presenter.AssetPresenter;
import swd.project.assetmanagement.view.AssetView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements AssetView {

    ListView listViewAsset;
    DepartmentListViewAdapter assetListViewAdapter;
    List<Asset> assetList;
    AssetPresenter assetPresenter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        listViewAsset = view.findViewById(R.id.listViewAsset);
////        assetPresenter = new AssetPresenter(this);
////        assetList = new ArrayList<>();
////        assetListViewAdapter = new DepartmentListViewAdapter(assetList);
////        listViewAsset.setAdapter(assetListViewAdapter);
////        assetPresenter.requestDataFromServer();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            transaction.remove(fragment);
        }
        transaction.add(R.id.assetContainer, new ItemAssetFragment(R.drawable.tiviss, "Tivi Samsung", "Tivi", "Room 123", "Active"));
        transaction.add(R.id.assetContainer, new ItemAssetFragment(R.drawable.tiviss, "Tivi Sony", "Tivi", "Room 123", "Prepair"));
        transaction.add(R.id.assetContainer, new ItemAssetFragment(R.drawable.tiviss, "Tivi Panasonic", "Tivi", "Room 123", "Broken"));
        transaction.commit();
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<Asset> movieArrayList) {
        assetList.addAll(movieArrayList);
        assetListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
