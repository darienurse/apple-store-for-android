package com.itunesstoreviewer.app;

import com.itunesstoreviewer.app.ItunesRssItemClasses.ItunesRSSResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darien on 3/2/2015.
 */
public class CategoryAttribute {
    public static final int LOAD = 199;
    private final String title;
    private final String titlePrefix;
    private final Integer color;
    private final String url;
    private final String playStoreKey;
    private ItunesRSSResponse rssResponse = null;
    private List<? extends ItunesItem> iTunesItems;

    public CategoryAttribute(String titlePrefix, String title, Integer color, String rssURL, String playStoreKey, List<ItunesItem> itunesItems) {
        this.titlePrefix = titlePrefix;
        this.title = title;
        this.color = color;
        if(title.equals("Album"))
            this.url ="https://itunes.apple.com/us/rss/"+rssURL+"/limit=" + LOAD + "/explicit=true//json";
        else
            this.url ="https://itunes.apple.com/us/rss/"+rssURL+"/limit=" + LOAD + "/json";
        this.playStoreKey = playStoreKey;
        this.iTunesItems = new ArrayList<ItunesItem>();
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
