package com.itunesstoreviewer.app;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.itunesstoreviewer.app.BaseClasses.AppController;

import java.util.*;

public class ItunesAppController extends AppController {

    public static List<ItunesItem> userFavorites;
    public static ColorDrawable globalColorController;
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
        return categoryAttributeList.size()-1;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String[] categories = getResources().getStringArray(R.array.itunes_categories);
        String[] categoryPrefixes = getResources().getStringArray(R.array.itunes_categories_prefix);
        String[] rssURLs = getResources().getStringArray(R.array.itunes_RSS_url);
        String[] playStoreKeys = getResources().getStringArray(R.array.play_store_keys);
        categoryAttributeList = new ArrayList<CategoryAttribute>(categories.length);
        appleToPlayStoreMap = new HashMap<String, String>();
        userFavorites = new ArrayList<ItunesItem>();
        globalColorController = new ColorDrawable();
        int[] colors = getResources().getIntArray(R.array.categoryColorArray);
        mInstance = this;

        CategoryAttribute categoryAttribute;
        for(int i = 0; i < categories.length; i++){
            categoryAttribute = new CategoryAttributeBuilder()
                    .setTitlePrefix(categoryPrefixes[i])
                    .setTitle(categories[i])
                    .setColor(colors[i])
                    .setRssURL(rssURLs[i])
                    .setPlayStoreKey(playStoreKeys[i])
                    .setItunesItems(null)
                    .createCategoryAttribute();
            categoryAttributeList.add(categoryAttribute);
        }

        categoryAttribute = new CategoryAttributeBuilder()
                .setTitlePrefix("")
                .setTitle("Favorites")
                .setColor(Color.BLACK)
                .setRssURL("")
                .setPlayStoreKey("")
                .setItunesItems(userFavorites)
                .createCategoryAttribute();
        categoryAttributeList.add(categoryAttribute);


        for (String entry : getResources().getStringArray(R.array.play_store_map)) {
            String[] splitResult = entry.split("\\|");
            appleToPlayStoreMap.put(splitResult[0], splitResult[2]);
            appleToPlayStoreMap.put(splitResult[1], splitResult[2]);
        }
    }
}