package com.itunesstoreviewer.app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.itunesstoreviewer.app.BaseClasses.AppController;

import java.util.*;

public class ItunesAppController extends AppController {

    public static final int LOAD = 199;
    public static List<ItunesItem> userFavorites;
    public static ColorDrawable globalColorController;
    private static String[] categories;
    private static List<CategoryAttribute> categoryAttributeList;
    private static Map<String, String> appleToPlayStoreMap;
    private static ItunesAppController mInstance;


    public static Intent generateShareIntent(ItunesItem itunesItem, String appName) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        String name = itunesItem.getTrackName();
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Details on " + name);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Checkout this content: " + name
                        + "(" + itunesItem.getTrackViewUrl() + ")"
                        + "\n provided by " + appName + " created by @darienurse");
        sendIntent.setType("text/plain");
        return sendIntent;
    }

    public static List<CategoryAttribute> getCategoryAttributeList() {
        return Collections.unmodifiableList(categoryAttributeList);
    }

    public static Map<String, String> getAppleToPlayStoreMap() {
        return Collections.unmodifiableMap(appleToPlayStoreMap);
    }

    public static synchronized ItunesAppController getInstance() {
        return mInstance;
    }

    public static int getNumCategories() {
        return categories.length;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        categories = getResources().getStringArray(R.array.itunes_categories);
        categoryAttributeList = new ArrayList<CategoryAttribute>(categories.length);
        appleToPlayStoreMap = new HashMap<String, String>();
        userFavorites = new ArrayList<ItunesItem>();
        globalColorController = new ColorDrawable();
        mInstance = this;
        categoryAttributeList.add(new CategoryAttribute("Top Grossing " + categories[0], getResources().getColor(R.color.green)
                , "https://itunes.apple.com/us/rss/topgrossingapplications/limit=" + LOAD + "/json"));
        categoryAttributeList.add(new CategoryAttribute("Top Grossing " + categories[1], getResources().getColor(R.color.yellow)
                , "https://itunes.apple.com/us/rss/topgrossingmacapps/limit=" + LOAD + "/json"));
        categoryAttributeList.add(new CategoryAttribute("Top " + categories[2], getResources().getColor(R.color.pink)
                , "https://itunes.apple.com/us/rss/topsongs/limit=" + LOAD + "/json"));
        categoryAttributeList.add(new CategoryAttribute("Top " + categories[3], getResources().getColor(R.color.orange)
                , "https://itunes.apple.com/us/rss/topalbums/limit=" + LOAD + "/explicit=true/json"));
        categoryAttributeList.add(new CategoryAttribute("Top " + categories[4], getResources().getColor(R.color.indigo)
                , "https://itunes.apple.com/us/rss/topmovies/limit=" + LOAD + "/json"));
        categoryAttributeList.add(new CategoryAttribute("Top " + categories[5], getResources().getColor(R.color.red)
                , "https://itunes.apple.com/us/rss/toptvepisodes/limit=" + LOAD + "/json"));
        categoryAttributeList.add(new CategoryAttribute("Top " + categories[6], getResources().getColor(R.color.light_blue)
                , "https://itunes.apple.com/us/rss/toppaidebooks/limit=" + LOAD + "/json"));
        categoryAttributeList.add(new CategoryAttribute("Top " + categories[7], getResources().getColor(R.color.purple)
                , "https://itunes.apple.com/us/rss/toppodcasts/limit=" + LOAD + "/json"));
        categoryAttributeList.add(new CategoryAttribute("Favorites", Color.BLACK, "", userFavorites));

        for (String entry : getResources().getStringArray(R.array.play_store_map)) {
            String[] splitResult = entry.split("\\|");
            appleToPlayStoreMap.put(splitResult[0], splitResult[2]);
            appleToPlayStoreMap.put(splitResult[1], splitResult[2]);
        }
    }
}