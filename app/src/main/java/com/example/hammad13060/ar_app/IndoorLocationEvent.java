package com.example.hammad13060.ar_app;

/**
 * Created by hammad13060 on 05/03/17.
 */

public class IndoorLocationEvent {
    public double x, y, z;
    public int level;

    public IndoorLocationEvent(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public IndoorLocationEvent(double x, double y, double z, int level) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.level = level;
    }
}
