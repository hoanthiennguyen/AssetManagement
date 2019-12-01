package swd.project.assetmanagement.model;

public class TransferRequest {
    Long id;
    Employee fromEmployee;
    Employee toEmployee;
    String createdTime;
    String status;
    Asset asset;
    boolean isSeenByReceiver;
    boolean isSeenBySender;

    public TransferRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getFromEmployee() {
        return fromEmployee;
    }

    public void setFromEmployee(Employee fromEmployee) {
        this.fromEmployee = fromEmployee;
    }

    public Employee getToEmployee() {
        return toEmployee;
    }

    public void setToEmployee(Employee toEmployee) {
        this.toEmployee = toEmployee;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public boolean isSeenByReceiver() {
        return isSeenByReceiver;
    }

    public void setSeenByReceiver(boolean seenByReceiver) {
        isSeenByReceiver = seenByReceiver;
    }

    public boolean isSeenBySender() {
        return isSeenBySender;
    }

    public void setSeenBySender(boolean seenBySender) {
        isSeenBySender = seenBySender;
    }
}
