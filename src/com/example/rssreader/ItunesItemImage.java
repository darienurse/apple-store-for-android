package com.example.rssreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Darien on 11/7/2014.
 */
public class ItunesItemImage implements Parcelable {
    public final URL small_url;
    public final int small_height;
    public final Bitmap small_bm;
    public final URL med_url;
    public final int med_height;
    public final Bitmap med_bm;
    public final URL big_url;
    public final int big_height;
    public final Bitmap big_bm;


    public ItunesItemImage(URL small_url,
                           int small_height,
                           URL med_url,
                           int med_height,
                           URL big_url,
                           int big_height) {
        this.small_url = small_url;
        this.small_height = small_height;
        this.small_bm =null;// getImageBitmap(small_url);
        this.med_url = med_url;
        this.med_height = med_height;
        this.med_bm =null;// getImageBitmap(med_url);
        this.big_url = big_url;
        this.big_height = big_height;
        this.big_bm = getImageBitmap(big_url);
    }

    private Bitmap getImageBitmap(URL url) {
        Bitmap bm = null;
        try {
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e("Debug", "Error getting bitmap", e);
        }
        return bm;
    }

    protected ItunesItemImage(Parcel in) {
        small_url = (URL) in.readValue(URL.class.getClassLoader());
        small_height = in.readInt();
        small_bm = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        med_url = (URL) in.readValue(URL.class.getClassLoader());
        med_height = in.readInt();
        med_bm = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
        big_url = (URL) in.readValue(URL.class.getClassLoader());
        big_height = in.readInt();
        big_bm = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(small_url);
        dest.writeInt(small_height);
        dest.writeValue(small_bm);
        dest.writeValue(med_url);
        dest.writeInt(med_height);
        dest.writeValue(med_bm);
        dest.writeValue(big_url);
        dest.writeInt(big_height);
        dest.writeValue(big_bm);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ItunesItemImage> CREATOR = new Parcelable.Creator<ItunesItemImage>() {
        @Override
        public ItunesItemImage createFromParcel(Parcel in) {
            return new ItunesItemImage(in);
        }

        @Override
        public ItunesItemImage[] newArray(int size) {
            return new ItunesItemImage[size];
        }
    };
}
