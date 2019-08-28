package com.example.canadanews.service.model;

import okhttp3.Headers;

public class ResponseModel {

    private Object object;
    private Throwable throwable;
    private Headers headers;
    public int errorCode;
    public boolean isSuccess = false;

    public ResponseModel() {
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object, boolean isSuccess, Headers headers, int errorCode) {
        this.object = object;
        this.isSuccess = isSuccess;
        this.headers = headers;
        this.errorCode = errorCode;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

}

