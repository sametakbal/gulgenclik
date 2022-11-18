package com.akbal.gulgenclik.common;

public class ServiceResult <T>{
    private boolean isSuccess;
    private T data;
    private String message;

    public ServiceResult(T data) {
        this.isSuccess = data !=null;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
