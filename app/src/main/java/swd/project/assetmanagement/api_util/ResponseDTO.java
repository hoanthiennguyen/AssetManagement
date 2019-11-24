package swd.project.assetmanagement.api_util;

public class ResponseDTO<T> {
    String status;
    T payload;
    Object errors;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public ResponseDTO() {
    }
}
