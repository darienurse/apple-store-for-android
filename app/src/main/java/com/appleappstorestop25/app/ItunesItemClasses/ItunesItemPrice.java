package com.appleappstorestop25.app.ItunesItemClasses;


import java.io.Serializable;

/**
 * Created by Darien on 11/7/2014.
 */
public class ItunesItemPrice implements Serializable {
    public final String type;
    public final double cost;
    public final String label;

    public ItunesItemPrice(String type, double cost, String label) {
        this.type = type;
        this.cost = cost;
        this.label = label;
    }
}
