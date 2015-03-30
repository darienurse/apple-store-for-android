package com.itunesstoreviewer.app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.itunesstoreviewer.app.BaseClasses.AppController;
import com.itunesstoreviewer.app.ItunesItemClasses.Entry;

import java.util.*;

public class ItunesAppController extends AppController {

    public static final int LOAD = 199;
    public static final int NUM_CATEGORIES = 6;
    public static final int NUM_MAPPINGS = 5;
    public static List<Entry> userFavorites;
    public static ColorDrawable globalColorController;
    private static List<CategoryAttribute> categoryList;
    private static Map<String, String> appleToPlayStoreMap;
    private static ItunesAppController mInstance;

    public static List<CategoryAttribute> getCategoryList() {
        return Collections.unmodifiableList(categoryList);
    }

    public static Map<String, String> getAppleToPlayStoreMap() {
        return Collections.unmodifiableMap(appleToPlayStoreMap);
    }

    public static synchronized ItunesAppController getInstance() {
        return mInstance;
    }

    public static Intent generateShareIntent(Entry itunesItem, String appName) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String name = itunesItem.getImName().getLabel();
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Details on " + name);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Checkout this content: " + name
                        + "(" + itunesItem.getLink().get(0).getAttributes().getHref() + ")"
                        + "\n provided by " + appName + " created by @darienurse");
        sendIntent.setType("text/plain");
        return sendIntent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        categoryList = new ArrayList<CategoryAttribute>(NUM_CATEGORIES);
        appleToPlayStoreMap = new HashMap<String, String>(NUM_MAPPINGS);
        userFavorites = new ArrayList<Entry>();
        globalColorController = new ColorDrawable();
        mInstance = this;
        categoryList.add(new CategoryAttribute("Top Grossing Apps", getResources().getColor(R.color.green)
                , "https://itunes.apple.com/us/rss/topgrossingapplications/limit=" + LOAD + "/json"));
        categoryList.add(new CategoryAttribute("Top Grossing Mac Apps", getResources().getColor(R.color.yellow)
                , "https://itunes.apple.com/us/rss/topgrossingmacapps/limit=" + LOAD + "/json"));
        categoryList.add(new CategoryAttribute("Top Songs", getResources().getColor(R.color.pink)
                , "https://itunes.apple.com/us/rss/topsongs/limit=" + LOAD + "/json"));
        categoryList.add(new CategoryAttribute("Top Albums", getResources().getColor(R.color.orange)
                , "https://itunes.apple.com/us/rss/topalbums/limit=" + LOAD + "/explicit=true/json"));
        categoryList.add(new CategoryAttribute("Top Movies", getResources().getColor(R.color.indigo)
                , "https://itunes.apple.com/us/rss/topmovies/limit=" + LOAD + "/json"));
        categoryList.add(new CategoryAttribute("Top TV Episodes", getResources().getColor(R.color.red)
                , "https://itunes.apple.com/us/rss/toptvepisodes/limit=" + LOAD + "/json"));
        categoryList.add(new CategoryAttribute("Top Books", getResources().getColor(R.color.light_blue)
                , "https://itunes.apple.com/us/rss/toppaidebooks/limit=" + LOAD + "/json"));
        categoryList.add(new CategoryAttribute("Top Podcasts", getResources().getColor(R.color.purple)
                , "https://itunes.apple.com/us/rss/toppodcasts/limit=" + LOAD + "/json"));
        categoryList.add(new CategoryAttribute("Favorites", Color.BLACK, "", userFavorites));
        appleToPlayStoreMap.put("Application", "apps");
        appleToPlayStoreMap.put("Podcast", "all");
        appleToPlayStoreMap.put("Music", "music");
        appleToPlayStoreMap.put("MZRssItemTypeIdentifier.Book", "books");
        appleToPlayStoreMap.put("TV Show", "tv");
        appleToPlayStoreMap.put("Track", "music");
        appleToPlayStoreMap.put("Movie", "movies");
    }
}