package swd.project.assetmanagement.presenter;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.Location;
import swd.project.assetmanagement.repository.LocationRepository;
import swd.project.assetmanagement.repository.LocationRepositoryImpl;
import swd.project.assetmanagement.view.LocationView;

public class LocationPresenter {
    LocationRepository repository = new LocationRepositoryImpl();
    LocationView locationView;

    public LocationPresenter(LocationView locationView) {
        this.locationView = locationView;
    }
    public void fetchAllBlocks(){
        repository.fetchAllBlocks(new CallbackData<List<String>>() {
            @Override
            public void onSuccess(List<String> strings) {
                locationView.onSuccessFetchBlock(strings);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    public void fetchAllFloorsOfBlock(String block){
        repository.fetchAllFloorOfBlock(block, new CallbackData<List<String>>() {
            @Override
            public void onSuccess(List<String> strings) {
                locationView.onSuccessFetchFloorOfBlock(strings);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    public void fetchAllLocationsOfBlockAndFloor(String block, String floor){
        repository.fetchAllLocationOfBlockAndFloor(block, floor, new CallbackData<List<Location>>() {
            @Override
            public void onSuccess(List<Location> locations) {
                locationView.onSuccessFetchRoomsOfBlockAndFloor(locations);
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
