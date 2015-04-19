package com.itunesstoreviewer.app;

import com.itunesstoreviewer.app.ItunesRssItemClasses.Entry;
import com.itunesstoreviewer.app.ItunesRssItemClasses.ItunesRSSResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darien on 3/2/2015.
 */
public class CategoryAttribute {
    private final String title;
    private final Integer color;
    private final String url;
    private ItunesRSSResponse rssResponse;
    private List<? extends ItunesItem> iTunesItems;

    public CategoryAttribute(String title, Integer c, String url) {
        this.title = title;
        this.color = c;
        this.url = url;
        this.rssResponse = null;
        this.iTunesItems = new ArrayList<ItunesItem>();
    }

    public CategoryAttribute(String title, Integer c, String url, List<ItunesItem> eList) {
        this.title = title;
        this.color = c;
        this.url = url;
        this.rssResponse = null;
        this.iTunesItems = eList;
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
        this.iTunesItems = rssResponse.getFeed().getEntry();
    }

    public List<? extends ItunesItem> getItunesItems() {
        return iTunesItems;
    }
}
