package swd.project.assetmanagement.model;

public class Asset {
    Long id;
    String name;
    Location location;
    AssetType assetType;
    Employee employee;
    String fromDate;
    String status;
    Long currentHistoryId;
    public Asset() {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCurrentHistoryId() {
        return currentHistoryId;
    }

    public void setCurrentHistoryId(Long currentHistoryId) {
        this.currentHistoryId = currentHistoryId;
    }
}
