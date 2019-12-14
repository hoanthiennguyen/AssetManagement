package swd.project.assetmanagement.model;

public class TransferRequest {
    Long id;
    Employee fromEmployee;
    Employee toEmployee;
    String createdTime;
    String status;
    Asset asset;
    Firm firm;
    boolean seenByReceiver;
    boolean seenBySender;
    String message;
    boolean close;

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

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public boolean isSeenByReceiver() {
        return seenByReceiver;
    }

    public void setSeenByReceiver(boolean seenByReceiver) {
        this.seenByReceiver = seenByReceiver;
    }

    public boolean isSeenBySender() {
        return seenBySender;
    }

    public void setSeenBySender(boolean seenBySender) {
        this.seenBySender = seenBySender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }
}
