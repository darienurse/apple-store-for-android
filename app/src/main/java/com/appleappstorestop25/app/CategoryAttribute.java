package com.appleappstorestop25.app;

import com.appleappstorestop25.app.ItunesItemClasses.ItunesRSSResponse;

import java.io.Serializable;

/**
 * Created by Darien on 3/2/2015.
 */
public class CategoryAttribute implements Serializable {
    private final String title;
    private final Integer color;
    private final String url;
    private ItunesRSSResponse rssResponse;

    public CategoryAttribute(String title, Integer c, String url) {
        this.title = title;
        this.color = c;
        this.url = url;
        this.rssResponse = null;
    }

    public String getUrl() {
        return url;
    }

    public Integer getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }

    public ItunesRSSResponse getRssResponse() {
        return rssResponse;
    }

    public void setRssResponse(ItunesRSSResponse rssResponse) {
        this.rssResponse = rssResponse;
    }
}
