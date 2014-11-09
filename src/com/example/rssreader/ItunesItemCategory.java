package com.example.rssreader;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by Darien on 11/7/2014.
 */
public class ItunesItemCategory implements Serializable {
    public final String category;
    public final double id;
    public final String term;
    public final URL scheme;
    public ItunesItemCategory(double id, String term, String category, URL scheme) {
        this.category = category;
        this.id = id;
        this.term = term;
        this.scheme = scheme;
    }
}
