package swd.project.assetmanagement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;
import java.util.List;

import swd.project.assetmanagement.adapter.LocationListAdapter;
import swd.project.assetmanagement.model.Location;
import swd.project.assetmanagement.presenter.LocationPresenter;
import swd.project.assetmanagement.view.LocationView;

public class PopupLocationDialog extends AppCompatDialogFragment implements LocationView {
    private HandleData handleData;
    Spinner spinnerBlock, spinnerFloor,spinnerRoom;
    String block,floor;
    Location selectedLocation,currentLocation;
    LocationPresenter locationPresenter;
    private List<Location> locationList;
    private List<String> blockList,floorList;
    LocationListAdapter locationAdapter;
    private ArrayAdapter<String> floorAdapter;
    private ArrayAdapter<String> blockAdapter;

    public PopupLocationDialog(HandleData handleData, Location currentLocation) {
        this.handleData = handleData;
        this.currentLocation = currentLocation;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_location_dialog,null);
        spinnerBlock = view.findViewById(R.id.spinnerBlock);
        spinnerFloor = view.findViewById(R.id.spinnerFloor);
        spinnerRoom = view.findViewById(R.id.spinnerRoom);

        locationPresenter = new LocationPresenter(this);
        locationPresenter.fetchAllBlocks();
        blockList = new ArrayList<>();
        blockAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, blockList);
        spinnerBlock.setAdapter(blockAdapter);

        floorList = new ArrayList<>();
        floorAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, floorList);
        spinnerFloor.setAdapter(floorAdapter);

        locationList = new ArrayList<>();
        locationAdapter = new LocationListAdapter(locationList);
        spinnerRoom.setAdapter(locationAdapter);

        setOnItemSelectedListener();
        builder.setView(view)
                .setTitle("Change Location")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handleData.processData(selectedLocation);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }

    private void setOnItemSelectedListener() {
        spinnerBlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                block = parent.getItemAtPosition(position).toString();
                locationPresenter.fetchAllFloorsOfBlock(block);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerFloor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                floor = parent.getItemAtPosition(position).toString();
                locationPresenter.fetchAllLocationsOfBlockAndFloor(block,floor);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation =(Location) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSuccessFetchBlock(List<String> blocks) {
        blockList.clear();
        blockList.addAll(blocks);
        blockAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessFetchFloorOfBlock(List<String> floors) {
        floorList.clear();
        floorList.addAll(floors);
        floorAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessFetchRoomsOfBlockAndFloor(List<Location> locations) {
        locationList.clear();
        locationList.addAll(locations);
        locationAdapter.notifyDataSetChanged();
    }

    public interface HandleData{
        void processData(Location location);
    }
}
