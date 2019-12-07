package swd.project.assetmanagement.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Location implements Serializable {
    Long id;
    String block;
    String floor;
    String room;

    public Location() {
    }

    public Location(Long id, String block, String floor, String room) {
        this.id = id;
        this.block = block;
        this.floor = floor;
        this.room = room;
    }

    public Location(String block, String floor, String room) {
        this.block = block;
        this.floor = floor;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @NonNull
    @Override
    public String toString() {
        return room;
    }
}
