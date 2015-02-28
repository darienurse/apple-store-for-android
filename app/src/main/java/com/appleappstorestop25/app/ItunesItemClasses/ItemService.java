package com.appleappstorestop25.app.ItunesItemClasses;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darien on 11/5/2014.
 */
public class ItemService {
    public List<ItunesItem> findAllItems() {
        System.out.print("testing1\n");
        JSONObject serviceResult = JSONManager.requestWebService(
                "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topgrossingapplications/sf=143441/limit=25/json");
        List<ItunesItem> foundItems = new ArrayList<ItunesItem>();

        try {
            JSONObject feed = serviceResult.getJSONObject("feed");
            System.out.println("NAME: " + feed.getJSONObject("author").getJSONObject("name").getString("label"));
            JSONArray items = feed.getJSONArray("entry");

            for (int i = 0; i < items.length(); i++) {
                JSONObject obj = items.getJSONObject(i);
                String name = obj.getJSONObject("im:name").getString("label");
                name = name.replaceAll("\\p{Pd}", "-");
                int dashIndex = name.indexOf("-");
                if (dashIndex != -1) {
                    name = name.substring(0, dashIndex).trim();
                }
                JSONArray images = obj.getJSONArray("im:image");
                ItunesItemImage image = new ItunesItemImage(new URL(images.getJSONObject(0).getString("label")),
                        images.getJSONObject(0).getJSONObject("attributes").getInt("height"),
                        new URL(images.getJSONObject(1).getString("label")),
                        images.getJSONObject(1).getJSONObject("attributes").getInt("height"),
                        new URL(images.getJSONObject(2).getString("label")),
                        images.getJSONObject(2).getJSONObject("attributes").getInt("height")
                        );
                String summary = obj.getJSONObject("summary").getString("label");
                ItunesItemPrice price = new ItunesItemPrice(obj.getJSONObject("im:price").getString("label"),
                        obj.getJSONObject("im:price").getJSONObject("attributes").getDouble("amount"),
                        obj.getJSONObject("im:price").getJSONObject("attributes").getString("currency")
                        );
                String contentType = obj.getJSONObject("im:contentType").getJSONObject("attributes").getString("term");
                String rights = obj.getJSONObject("rights").getString("label");
                String title = obj.getJSONObject("title").getString("label");
                URL link = new URL(obj.getJSONObject("link").getJSONObject("attributes").getString("href"));
                Integer id = obj.getJSONObject("id").getJSONObject("attributes").getInt("im:id");
                String bundleId = obj.getJSONObject("id").getJSONObject("attributes").getString("im:bundleId");
                String artist = obj.getJSONObject("im:artist").getString("label");
                ItunesItemCategory scheme = new ItunesItemCategory(
                        obj.getJSONObject("category").getJSONObject("attributes").getDouble("im:id"),
                        obj.getJSONObject("category").getJSONObject("attributes").getString("term"),
                        obj.getJSONObject("category").getJSONObject("attributes").getString("label"),
                        new URL(obj.getJSONObject("category").getJSONObject("attributes").getString("scheme"))
                );
                String releaseDate = obj.getJSONObject("im:releaseDate").getJSONObject("attributes").getString("label");
                foundItems.add(new ItunesItemBuilder()
                        .setName(name)
                        .setImage(image)
                        .setSummary(summary)
                        .setPrice(price)
                        .setContentType(contentType)
                        .setRights(rights)
                        .setTitle(title)
                        .setLink(link)
                        .setId(id)
                        .setBundleId(bundleId)
                        .setArtist(artist)
                        .setCategoryObject(scheme)
                        .setReleaseDate(releaseDate)
                        .setRank(i)
                        .createItunesItem());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return foundItems;
    }


}
