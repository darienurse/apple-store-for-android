package com.example.rssreader;

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
                Log.d("Itunes "+i, name);
                JSONArray images = obj.getJSONArray("im:image");
                ItunesItemImage image = new ItunesItemImage(new URL(images.getJSONObject(0).getString("label")),
                        images.getJSONObject(0).getJSONObject("attributes").getInt("height"),
                        new URL(images.getJSONObject(1).getString("label")),
                        images.getJSONObject(1).getJSONObject("attributes").getInt("height"),
                        new URL(images.getJSONObject(2).getString("label")),
                        images.getJSONObject(2).getJSONObject("attributes").getInt("height")
                        );
                String summary = obj.getJSONObject("summary").getString("label");
                Log.d("Itunes "+i, summary);
                ItunesItemPrice price = new ItunesItemPrice(obj.getJSONObject("im:price").getString("label"),
                        obj.getJSONObject("im:price").getJSONObject("attributes").getDouble("amount"),
                        obj.getJSONObject("im:price").getJSONObject("attributes").getString("currency")
                        );
                Log.d("Itunes "+i, price.label);
                String contentType = obj.getJSONObject("im:contentType").getJSONObject("attributes").getString("term");
                Log.d("Itunes "+i, contentType);
                String rights = obj.getJSONObject("rights").getString("label");
                Log.d("Itunes "+i, rights);
                String title = obj.getJSONObject("title").getString("label");
                Log.d("Itunes "+i, title);
                URL link = new URL(obj.getJSONObject("link").getJSONObject("attributes").getString("href"));
                Log.d("Itunes "+i, link.toString());
                Integer id = obj.getJSONObject("id").getJSONObject("attributes").getInt("im:id");
                Log.d("Itunes "+i, id.toString());
                String bundleId = obj.getJSONObject("id").getJSONObject("attributes").getString("im:bundleId");
                Log.d("Itunes "+i, bundleId);
                String artist = obj.getJSONObject("im:artist").getString("label");
                Log.d("Itunes "+i, artist);
                ItunesItemCategory scheme = new ItunesItemCategory(
                        obj.getJSONObject("category").getJSONObject("attributes").getDouble("im:id"),
                        obj.getJSONObject("category").getJSONObject("attributes").getString("term"),
                        obj.getJSONObject("category").getJSONObject("attributes").getString("label"),
                        new URL(obj.getJSONObject("category").getJSONObject("attributes").getString("scheme"))
                );
                Log.d("Itunes "+i, scheme.category);
                String releaseDate = obj.getJSONObject("im:releaseDate").getJSONObject("attributes").getString("label");
                Log.d("Itunes "+i, releaseDate);
                foundItems.add(new ItunesItem(name,
                        image,
                        summary,
                        price,
                        contentType,
                        rights,
                        title,
                        link,
                        id,
                        bundleId,
                        artist,
                        scheme,
                        releaseDate));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return foundItems;
    }
}
