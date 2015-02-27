package com.appleappstorestop25.app.ItunesItemClasses;

import android.os.Parcel;
import android.os.Parcelable;

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
category ---id, term, category, label
releaseDate
*/
public class ItunesItem implements Parcelable {
    public final String
            name;
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
    public final ItunesItemCategory category;
    public final String releaseDate;

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
                      ItunesItemCategory category,
                      String releaseDate){
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
        this.category = category;
        this.releaseDate = releaseDate;
    }

    protected ItunesItem(Parcel in) {
        name = in.readString();
        image = (ItunesItemImage) in.readValue(ItunesItemImage.class.getClassLoader());
        summary = in.readString();
        price = (ItunesItemPrice) in.readValue(ItunesItemPrice.class.getClassLoader());
        contentType = in.readString();
        rights = in.readString();
        title = in.readString();
        link = (URL) in.readValue(URL.class.getClassLoader());
        id = in.readByte() == 0x00 ? null : in.readInt();
        bundleId = in.readString();
        artist = in.readString();
        category = (ItunesItemCategory) in.readValue(ItunesItemCategory.class.getClassLoader());
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeValue(image);
        dest.writeString(summary);
        dest.writeValue(price);
        dest.writeString(contentType);
        dest.writeString(rights);
        dest.writeString(title);
        dest.writeValue(link);
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(bundleId);
        dest.writeString(artist);
        dest.writeValue(category);
        dest.writeString(releaseDate);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ItunesItem> CREATOR = new Parcelable.Creator<ItunesItem>() {
        @Override
        public ItunesItem createFromParcel(Parcel in) {
            return new ItunesItem(in);
        }

        @Override
        public ItunesItem[] newArray(int size) {
            return new ItunesItem[size];
        }
    };
}
