package swd.project.assetmanagement.model;

public class TransferRequest {
    Long id;
    Employee sender;
    Employee receiver;
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

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }

    public Employee getReceiver() {
        return receiver;
    }

    public void setReceiver(Employee receiver) {
        this.receiver = receiver;
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
