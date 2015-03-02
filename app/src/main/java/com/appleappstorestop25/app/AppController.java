package com.appleappstorestop25.app;

import android.app.Application;
import android.graphics.Color;
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
    public static  List<CategoryAttribute> categoryList = new ArrayList<CategoryAttribute>();

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private static AppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        categoryList.add(new CategoryAttribute("Top Grossing Apps", Color.RED, "https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/json"));
        categoryList.add(new CategoryAttribute("Top Free Apps", Color.BLUE, "https://itunes.apple.com/us/rss/topfreeapplications/limit=25/json"));
        categoryList.add(new CategoryAttribute("Top Songs", Color.GREEN, "https://itunes.apple.com/us/rss/topgrossingapplications/limit=25/json"));
        categoryList.add(new CategoryAttribute("Top Albums", Color.YELLOW, "https://itunes.apple.com/us/rss/topfreeapplications/limit=25/json"));
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