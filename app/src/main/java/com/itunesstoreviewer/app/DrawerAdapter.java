package com.itunesstoreviewer.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.itunesstoreviewer.app.ItunesItemClasses.Entry;

import java.util.List;

/**
 * Created by Darien on 3/4/2015.
 */
public class DrawerAdapter extends BaseAdapter {
    ImageLoader imageLoader = ItunesAppController.getInstance().getImageLoader();
    private Context context;
    private LayoutInflater inflater;
    private List<Entry> itunesItems;

    public DrawerAdapter(Context context, List<Entry> itunesItems) {
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
            convertView = inflater.inflate(R.layout.drawer_list_row, null);

        if (imageLoader == null)
            imageLoader = ItunesAppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView artist = (TextView) convertView.findViewById(R.id.artist);

        // getting app data for the row
        Entry itunesItem = itunesItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(itunesItem.getImImage().get(2).getLabel(), imageLoader);

        // name
        name.setText(itunesItem.getImName().getLabel());

        // artist
        artist.setText(itunesItem.getImArtist().getLabel());

        return convertView;
    }

}