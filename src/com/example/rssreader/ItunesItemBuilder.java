package com.example.rssreader;

import android.os.Parcel;

import java.net.URL;

public class ItunesItemBuilder {
    private String name;
    private ItunesItemImage image = null;
    private String summary;
    private ItunesItemPrice price;
    private String contentType;
    private String rights;
    private String title;
    private URL link;
    private Integer id;
    private String bundleId;
    private String artist;
    private ItunesItemCategory category;
    private String releaseDate;

    public ItunesItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItunesItemBuilder setImage(ItunesItemImage image) {
        this.image = image;
        return this;
    }

    public ItunesItemBuilder setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public ItunesItemBuilder setPrice(ItunesItemPrice price) {
        this.price = price;
        return this;
    }

    public ItunesItemBuilder setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public ItunesItemBuilder setRights(String rights) {
        this.rights = rights;
        return this;
    }

    public ItunesItemBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ItunesItemBuilder setLink(URL link) {
        this.link = link;
        return this;
    }

    public ItunesItemBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public ItunesItemBuilder setBundleId(String bundleId) {
        this.bundleId = bundleId;
        return this;
    }

    public ItunesItemBuilder setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public ItunesItemBuilder setCategory(ItunesItemCategory category) {
        this.category = category;
        return this;
    }

    public ItunesItemBuilder setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public ItunesItem createItunesItem() {
        return new ItunesItem(name, image, summary, price, contentType, rights, title, link, id, bundleId, artist, category, releaseDate);
    }
}