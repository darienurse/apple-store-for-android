package com.appleappstorestop25.app;

/**
 * Created by Darien on 9/27/2014.
 */
import android.content.Context;
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
    private Context context;
    private LayoutInflater inflater;
    private List<Entry> itunesItems;
    ImageLoader imageLoader = ItunesAppController.getInstance().getImageLoader();

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
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);;
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView artist = (TextView) convertView.findViewById(R.id.artist);
        TextView category = (TextView) convertView.findViewById(R.id.contentType);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        // getting app data for the row
        Entry itunesItem = itunesItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(itunesItem.getImImage().get(2).getLabel(), imageLoader);

        // name
        name.setText((position+1)+". "+itunesItem.getFormattedName());

        // artist
        artist.setText(itunesItem.getImArtist().getLabel());

        // categoryObject
        category.setText(itunesItem.getCategory().getAttributes().getTerm());

        // release date
        date.setText(itunesItem.getImReleaseDate().getAttributes().getLabel());

        return convertView;
    }

}