package com.example.myapplmh.json;

public class JsonData {
    private int errno;
    private Data data;
    private String error;

    public int getErrno() {
        return errno;
    }

    public Data getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }
}
