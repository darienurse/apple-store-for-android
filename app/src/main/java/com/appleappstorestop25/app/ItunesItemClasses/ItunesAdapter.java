package com.appleappstorestop25.app.ItunesItemClasses;

/**
 * Created by Darien on 9/27/2014.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.appleappstorestop25.app.R;

import java.util.List;

public class ItunesAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<ItunesItem> itunesItems;
    //ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ItunesAdapter(Activity activity, List<ItunesItem> itunesItems) {
        this.activity = activity;
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
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        /*if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();*/
        ImageView thumbNail = (ImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView artist = (TextView) convertView.findViewById(R.id.artist);
        TextView category = (TextView) convertView.findViewById(R.id.contentType);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        // getting movie data for the row
        ItunesItem m = itunesItems.get(position);

        // thumbnail image
        //thumbNail..setImageUrl(m.getThumbnailUrl(), imageLoader);
        thumbNail.setImageBitmap(m.image.big_bm);

        // name
        name.setText(m.name);

        // artist
        artist.setText(String.valueOf(m.artist));

        // categoryObject
        category.setText(m.categoryObject.category);

        // release date
        date.setText("Released: " + m.releaseDate);

        return convertView;
    }

}