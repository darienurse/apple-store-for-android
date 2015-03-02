package com.appleappstorestop25.app;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    public static final int LOAD = 50;

    public static  List<CategoryAttribute> categoryList = new ArrayList<CategoryAttribute>();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        categoryList.add(new CategoryAttribute("Top Grossing Apps", getResources().getColor(R.color.green), "https://itunes.apple.com/us/rss/topgrossingapplications/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Grossing Mac Apps", getResources().getColor(R.color.yellow), "https://itunes.apple.com/us/rss/topgrossingmacapps/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Albums", getResources().getColor(R.color.orange), "https://itunes.apple.com/us/rss/topalbums/limit="+LOAD+"/explicit=true/json"));
        categoryList.add(new CategoryAttribute("Top TV Seasons", getResources().getColor(R.color.red), "https://itunes.apple.com/us/rss/toptvseasons/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Books", getResources().getColor(R.color.blue), "https://itunes.apple.com/us/rss/toppaidebooks/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Podcasts", getResources().getColor(R.color.purple), "https://itunes.apple.com/us/rss/toppodcasts/limit="+LOAD+"/json"));
    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}