package com.example.rssreader;

/**
 * Created by Darien on 9/27/2014.
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ItunesAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final Bitmap[] imageId;
    public ItunesAdapter(Activity context,
                         String[] appNames, Bitmap[] appImages ) {
        super(context, R.layout.list_item, appNames);
        this.context = context;
        this.web = appNames;
        this.imageId = appImages;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        //imageView.setImageResource(imageId[position]);
        imageView.setImageBitmap(imageId[position]);
        return rowView;
    }
}