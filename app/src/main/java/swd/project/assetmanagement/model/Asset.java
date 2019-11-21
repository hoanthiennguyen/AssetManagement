package swd.project.assetmanagement.model;

public class Asset {
    Long id;
    String name;
    Location location;
    AssetType assetType;

    public Asset(Long id, String name, Location location, AssetType assetType) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.assetType = assetType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }
}
