package com.ajwalker.entity;

public enum OdaType {
    TEK(0.3),
    CIFT(0.5),
    AILE(0.7);

    private double rate;
     OdaType(double rate) {
        this.rate = rate;
    }
    public double getRate() {
         return rate;
    }
}
