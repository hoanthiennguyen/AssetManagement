package swd.project.assetmanagement.view;

import java.util.List;

import swd.project.assetmanagement.model.Location;

public interface LocationView {
    void onSuccessFetchBlock(List<String> blocks);
    void onSuccessFetchFloorOfBlock(List<String> floors);
    void onSuccessFetchRoomsOfBlockAndFloor(List<Location> locations);
}
