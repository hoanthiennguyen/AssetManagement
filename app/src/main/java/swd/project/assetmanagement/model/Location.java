package swd.project.assetmanagement.model;

public class Location {
    Long id;
    String block;
    String floor;
    String room;

    public Location(Long id, String block, String floor, String room) {
        this.id = id;
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
}
