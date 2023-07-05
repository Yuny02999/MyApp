package com.example.myapplmh.json;

import java.util.List;

public class Data {
    int count;
    List<DatastreamsItem> datastreams;

    public int getCount() {
        return count;
    }

    public List<DatastreamsItem> getDatastreams() {
        return datastreams;
        //JsonData不允许解析大写字符
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDataStreams(List<DatastreamsItem> dataStreams) {
        this.datastreams = dataStreams;
    }
}
