package com.itunesstoreviewer.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ItunesRSSResponse implements Serializable {

    @Expose
    private Feed feed;

    /**
     * @return The feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * @param feed The feed
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
    }

}
