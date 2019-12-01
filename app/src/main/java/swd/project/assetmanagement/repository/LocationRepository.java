package swd.project.assetmanagement.repository;

import java.util.List;

import swd.project.assetmanagement.api_util.CallbackData;
import swd.project.assetmanagement.model.Location;

public interface LocationRepository {
    void fetchAllBlocks(CallbackData<List<String>> callbackData);
    void fetchAllFloorOfBlock(String block, CallbackData<List<String>> callbackData);
    void fetchAllLocationOfBlockAndFloor(String block,String floor, CallbackData<List<Location>> callbackData);
}
