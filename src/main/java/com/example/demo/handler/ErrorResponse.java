package com.example.demo.handler;

public class ErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timeStamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
