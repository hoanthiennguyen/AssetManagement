package swd.project.assetmanagement;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import swd.project.assetmanagement.adapter.AssetListViewAdapter;
import swd.project.assetmanagement.model.Asset;
import swd.project.assetmanagement.model.AssetType;
import swd.project.assetmanagement.model.Location;
import swd.project.assetmanagement.model.LoginDTO;
import swd.project.assetmanagement.presenter.AssetListPresenter;
import swd.project.assetmanagement.presenter.AssetTypeListPresenter;
import swd.project.assetmanagement.presenter.LocationPresenter;
import swd.project.assetmanagement.view.AssetListView;
import swd.project.assetmanagement.view.AssetTypeListView;
import swd.project.assetmanagement.view.LoadingView;
import swd.project.assetmanagement.view.LocationView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements AssetListView, LoadingView, AssetTypeListView, LocationView {
    View view;

    ListView listViewAsset;
    AssetListViewAdapter assetListViewAdapter;
    List<Asset> assetList;
    AssetListPresenter assetListPresenter;

    Spinner sStatus, sTypes, sFloor, sRoom;
    List<AssetType> assetTypes;
    AssetTypeListPresenter assetTypeListPresenter;

    List<String> floorsList;
    List<Location> roomsList;
    LocationPresenter locationPresenter;

    Button btnFilter;
    String room, status;
    Long assetTypeId;
    LoginDTO employee;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        MainActivity mainActivity = (MainActivity) view.getContext();

        employee = mainActivity.employee;
        listViewAsset = view.findViewById(R.id.assetContainer);
        assetListPresenter = new AssetListPresenter(this,this);
        assetList = new ArrayList<>();
        assetListViewAdapter = new AssetListViewAdapter(assetList);
        listViewAsset.setAdapter(assetListViewAdapter);
        assetListPresenter.fetchListAssetFromServer(employee.getEmployee().getId());

        listViewAsset.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Asset asset = assetList.get(position);
                Intent intent = new Intent(getActivity(),AssetDetailsActivity.class);
                intent.putExtra("asset",asset);
                intent.putExtra("employee",employee);
                startActivityForResult(intent,200);
            }
        });

        sStatus = view.findViewById(R.id.spinnerStatus);
        ArrayAdapter<CharSequence> adapterStatus = ArrayAdapter.createFromResource(view.getContext(), R.array.status, android.R.layout.simple_spinner_item);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sStatus.setAdapter(adapterStatus);
        sStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String sta = (String) adapterView.getSelectedItem();
                if(i == 0) {
                    status = null;
                }else {
                    status = sta;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        assetTypes = new ArrayList<>();
        assetTypeListPresenter = new AssetTypeListPresenter(this, this);
        assetTypeListPresenter.fetchAssetTypeFromServer();
        sTypes = view.findViewById(R.id.spinnerType);

        floorsList = new ArrayList<>();
        roomsList = new ArrayList<>();
        locationPresenter = new LocationPresenter(this);
        locationPresenter.fetchAllFloorsOfBlock("A");
        sFloor = view.findViewById(R.id.spinnerFloor);
        sRoom = view.findViewById(R.id.spinnerRoom);
        sRoom.setEnabled(false);

        btnFilter = view.findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetListPresenter.filterAssetFromServer(employee.getEmployee().getId(), room, status, assetTypeId);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 200 && resultCode == Activity.RESULT_OK && data.getBooleanExtra("updated",false)){
            assetListPresenter.fetchListAssetFromServer(employee.getEmployee().getId());
        }
    }

    @Override
    public void onSuccessFetchAssetList(List<Asset> movieArrayList) {
        assetList.clear();
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

    @Override
    public void onSuccessFetchAssetType(List<AssetType> movieArrayList) {
        assetTypes.clear();
        AssetType assetType = new AssetType();
        assetType.setName("Choose Type");
        assetTypes.add(0, assetType);
        assetTypes.addAll(movieArrayList);
        ArrayAdapter<AssetType> adapterAssetType = new ArrayAdapter<AssetType>(view.getContext(), android.R.layout.simple_spinner_item, assetTypes);
        adapterAssetType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sTypes.setAdapter(adapterAssetType);
        sTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AssetType assetType = (AssetType) adapterView.getSelectedItem();
                if(i == 0) {
                    assetTypeId = null;
                }else {
                    assetTypeId = assetType.getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onFailureFetchAssetType(String throwable) {
        Toast.makeText(getContext(), throwable, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessFetchBlock(List<String> blocks) {

    }

    @Override
    public void onSuccessFetchFloorOfBlock(List<String> floors) {
        floorsList.clear();
        floorsList.add(0, "Choose Floor");
        floorsList.addAll(floors);
        ArrayAdapter<String> adapterFloor = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, floorsList);
        adapterFloor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sFloor.setAdapter(adapterFloor);
        sFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String floor = (String) adapterView.getSelectedItem();
                if(i == 0) {
                    sRoom.setEnabled(false);
                }else {
                    sRoom.setEnabled(true);
                    locationPresenter.fetchAllLocationsOfBlockAndFloor("A", floor);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onSuccessFetchRoomsOfBlockAndFloor(List<Location> locations) {
        roomsList.clear();
        roomsList.addAll(locations);
        ArrayAdapter<Location> adapterLocation = new ArrayAdapter<Location>(view.getContext(), android.R.layout.simple_spinner_item, roomsList);
        adapterLocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sRoom.setAdapter(adapterLocation);
        sRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Location location = (Location) adapterView.getSelectedItem();
                room = location.getRoom();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
