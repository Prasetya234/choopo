package com.example.choopo.web.service.response;

public class CommonResponse<T> {

    private String status;
    private String message;
    private T contet;

    public CommonResponse() {
    }

    public CommonResponse(String status, String message, T contet) {
        this.status = status;
        this.message = message;
        this.contet = contet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContet() {
        return contet;
    }

    public void setContet(T contet) {
        this.contet = contet;
    }
}
