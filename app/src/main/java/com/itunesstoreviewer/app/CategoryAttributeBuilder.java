package com.itunesstoreviewer.app;

import java.util.List;

public class CategoryAttributeBuilder {
    private String titlePrefix;
    private String title;
    private Integer color;
    private String rssURL;
    private String playStoreKey;
    private List<ItunesItem> itunesItems;

    public CategoryAttributeBuilder setTitlePrefix(String titlePrefix) {
        this.titlePrefix = titlePrefix;
        return this;
    }

    public CategoryAttributeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CategoryAttributeBuilder setColor(Integer color) {
        this.color = color;
        return this;
    }

    public CategoryAttributeBuilder setRssURL(String rssURL) {
        this.rssURL = rssURL;
        return this;
    }

    public CategoryAttributeBuilder setPlayStoreKey(String playStoreKey) {
        this.playStoreKey = playStoreKey;
        return this;
    }

    public CategoryAttributeBuilder setItunesItems(List<ItunesItem> itunesItems) {
        this.itunesItems = itunesItems;
        return this;
    }

    public CategoryAttribute createCategoryAttribute() {
        return new CategoryAttribute(titlePrefix, title, color, rssURL, playStoreKey, itunesItems);
    }
}