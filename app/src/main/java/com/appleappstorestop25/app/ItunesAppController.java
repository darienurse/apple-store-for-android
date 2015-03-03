package com.appleappstorestop25.app;

import android.app.Application;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.appleappstorestop25.app.BaseClasses.AppController;

import java.util.ArrayList;
import java.util.List;

public class ItunesAppController extends AppController {

    public static final int LOAD = 50;

    public static  List<CategoryAttribute> categoryList;

    public static ColorDrawable globalColorController;

    private static ItunesAppController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        categoryList = new ArrayList<CategoryAttribute>();
        globalColorController = new ColorDrawable();
        mInstance = this;
        categoryList.add(new CategoryAttribute("Top Grossing Apps", getResources().getColor(R.color.green), "https://itunes.apple.com/us/rss/topgrossingapplications/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Grossing Mac Apps", getResources().getColor(R.color.yellow), "https://itunes.apple.com/us/rss/topgrossingmacapps/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Albums", getResources().getColor(R.color.orange), "https://itunes.apple.com/us/rss/topalbums/limit="+LOAD+"/explicit=true/json"));
        categoryList.add(new CategoryAttribute("Top TV Seasons", getResources().getColor(R.color.red), "https://itunes.apple.com/us/rss/toptvseasons/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Books", getResources().getColor(R.color.blue), "https://itunes.apple.com/us/rss/toppaidebooks/limit="+LOAD+"/json"));
        categoryList.add(new CategoryAttribute("Top Podcasts", getResources().getColor(R.color.purple), "https://itunes.apple.com/us/rss/toppodcasts/limit="+LOAD+"/json"));
    }

    public static synchronized ItunesAppController getInstance() {
        return mInstance;
    }
}