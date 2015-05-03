package com.itunesstoreviewer.app;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.itunesstoreviewer.app.BaseClasses.AppController;

import java.util.*;

public class ItunesAppController extends AppController {

    public static List<ItunesItem> userFavorites;
    private static List<CategoryAttribute> categoryAttributeList;
    private static Map<String, CategoryAttribute> contentTypeMap;

    public static List<CategoryAttribute> getCategoryAttributeList() {
        return Collections.unmodifiableList(categoryAttributeList);
    }

    public static CategoryAttribute getCategoryAttribute(ItunesItem itunesItem) {
        return contentTypeMap.get(itunesItem.getContentType());
    }

    public static int getNumCategories() {
        return categoryAttributeList.size() - 1;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String[] categories = getResources().getStringArray(R.array.itunes_categories);
        String[] categoryPrefixes = getResources().getStringArray(R.array.itunes_categories_prefix);
        String[] rssURLs = getResources().getStringArray(R.array.itunes_RSS_url);
        String[] playStoreKeys = getResources().getStringArray(R.array.play_store_keys);
        String[] itunesContentTypes = getResources().getStringArray(R.array.RSS_and_search_content_types);
        int[] colors = getResources().getIntArray(R.array.categoryColorArray);
        categoryAttributeList = new ArrayList<CategoryAttribute>(categories.length);
        contentTypeMap = new HashMap<String, CategoryAttribute>();
        userFavorites = new ArrayList<ItunesItem>();

        CategoryAttribute categoryAttribute;
        for (int i = 0; i < categories.length; i++) {
            categoryAttribute = new CategoryAttributeBuilder()
                    .setTitlePrefix(categoryPrefixes[i])
                    .setTitle(categories[i])
                    .setColor(colors[i])
                    .setRssURL(rssURLs[i])
                    .setPlayStoreKey(playStoreKeys[i])
                    .setItunesItems(null)
                    .createCategoryAttribute();
            categoryAttributeList.add(categoryAttribute);
            String[] splitResult = itunesContentTypes[i].split("\\|");
            for(String categoryType : splitResult)
                contentTypeMap.put(categoryType, categoryAttribute);
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
    }
}