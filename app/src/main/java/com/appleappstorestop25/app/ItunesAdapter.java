package com.appleappstorestop25.app;

/**
 * Created by Darien on 9/27/2014.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;

import java.util.List;

public class ItunesAdapter extends BaseAdapter {
    ImageLoader imageLoader = ItunesAppController.getInstance().getImageLoader();
    private Context context;
    private LayoutInflater inflater;
    private List<Entry> itunesItems;
    private String mName;
    private String mUrl;
    private String mCategory;
    private String mPrice;
    private String mArtist;

    public ItunesAdapter(Context context, List<Entry> itunesItems) {
        this.context = context;
        this.itunesItems = itunesItems;
    }

    @Override
    public int getCount() {
        return itunesItems.size();
    }

    @Override
    public Object getItem(int location) {
        return itunesItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = ItunesAppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView artist = (TextView) convertView.findViewById(R.id.artist);
        TextView category = (TextView) convertView.findViewById(R.id.contentType);
        TextView price = (TextView) convertView.findViewById(R.id.price);

        // getting app data for the row
        Entry itunesItem = itunesItems.get(position);
        try {
            mUrl = itunesItem.getImImage().get(2).getLabel();
            mName = itunesItem.getImName().getLabel();
            mArtist = itunesItem.getImArtist().getLabel();
            mCategory = itunesItem.getCategory().getAttributes().getTerm();
            mPrice = itunesItem.getImPrice().getLabel().replaceFirst("-", "");
            mPrice = (mPrice.equals("Get")) ? "Free" : mPrice;
        } catch (NullPointerException e) {
            Log.e(ItunesAdapter.class.getName(), e.toString() +
                    "\nCaused by dereferencing " + mName + "at index " + position);
        }

        // thumbnail image
        thumbNail.setImageUrl(mUrl, imageLoader);

        // name
        name.setText((position + 1) + ". " + mName);

        // artist
        artist.setText(mArtist);

        // categoryObject
        category.setText(mCategory);

        // price
        price.setText(mPrice);
        return convertView;
    }

}