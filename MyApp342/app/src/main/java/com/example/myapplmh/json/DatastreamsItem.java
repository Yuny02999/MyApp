package com.example.myapplmh.json;

import java.util.List;

public class DatastreamsItem {
    String id;
    List<DatapointsItem> datapoints;

    public String getId() {
        return id;
    }

    public List<DatapointsItem> getDatapoints() {
        return datapoints;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDatapoints(List<DatapointsItem> datapoints) {
        this.datapoints = datapoints;
    }
}
