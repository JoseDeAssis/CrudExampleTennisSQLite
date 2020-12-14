package com.example.crudexampletennissqlite;

import java.io.Serializable;

public class TennisModel implements Serializable {
    private String tennisName;
    private long tennisId;
    private double tennisPrice;

    public String getTennisName() {
        return tennisName;
    }

    public void setTennisName(String tennisName) {
        this.tennisName = tennisName;
    }

    public long getTennisId() {
        return tennisId;
    }

    public void setTennisId(long tennisId) {
        this.tennisId = tennisId;
    }

    public double getTennisPrice() {
        return tennisPrice;
    }

    public void setTennisPrice(double tennisPrice) {
        this.tennisPrice = tennisPrice;
    }

}
