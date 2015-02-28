package com.appleappstorestop25.app.ItunesItemClasses;

import java.net.URL;

/**
 * Created by Darien on 11/7/2014.
 */


/*
Itunes Item details
Name
Image ---> small_url, small_height, med_url, med_height, big_url, big_height
summary
Price ---> type, cost, currency
contentType
rights
title
link
id
bundleId
artist
categoryObject ---id, term, categoryObject, label
releaseDate
*/
public class ItunesItem {
    public final String name;
    public final ItunesItemImage image;
    public final String summary;
    public final ItunesItemPrice price;
    public final String contentType;
    public final String rights;
    public final String title;
    public final URL link;
    public final Integer id;
    public final String bundleId;
    public final String artist;
    public final ItunesItemCategory categoryObject;
    public final String releaseDate;
    public final Integer rank;

    public ItunesItem(String name,
                      ItunesItemImage image,
                      String summary,
                      ItunesItemPrice price,
                      String contentType,
                      String rights,
                      String title,
                      URL link,
                      Integer id,
                      String bundleId,
                      String artist,
                      ItunesItemCategory categoryObject,
                      String releaseDate,
                      Integer rank) {
        this.name = name;
        this.image = image;
        this.summary = summary;
        this.price = price;
        this.contentType = contentType;
        this.rights = rights;
        this.title = title;
        this.link = link;
        this.id = id;
        this.bundleId = bundleId;
        this.artist = artist;
        this.categoryObject = categoryObject;
        this.releaseDate = releaseDate;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return name;
    }

}
