package com.example.n20dccn143_levietthanh.response;

public class ApiResponse {

    private String message;
    private boolean status;
    private int code;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public ApiResponse(String message, boolean status, int code) {
        super();
        this.message = message;
        this.status = status;
        this.code = code;
    }
    public ApiResponse() {
        super();
        // TODO Auto-generated constructor stub
    }




}
