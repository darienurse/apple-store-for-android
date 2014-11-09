package com.example.rssreader;

/**
 * Created by Darien on 9/27/2014.
 */
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class ItunesListActivity extends ListActivity {
    private final String FAV = "Favorites";
    private final String TOP25 = "Top 25";
    private List<ItunesItem> itemsList;
    private HashMap<String, ItunesItem> itemsMap = new HashMap<String, ItunesItem>();
    private SharedPreferences sharedpreferences;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        itemsList = MainActivity.getItemsList();
        initialListSetup();
        setupPrefs();

    }
    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, ItunesDetailsActivity.class);
        String appName = ((TextView)v.findViewById(R.id.txt)).getText().toString();
        ItunesItem selected = itemsMap.get(appName);
        if(selected != null){
            intent.putExtra("item", selected);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate share_menu resource file.
        getMenuInflater().inflate(R.menu.fav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mItem){
        ArrayList<String> prefValues = new ArrayList<String>(sharedpreferences.getStringSet(FAV, null));
        if(mItem.getItemId() == R.id.menu_fav){
            if(mItem.getTitle().equals(FAV)) {
                populateFavList(prefValues);
                mItem.setIcon(R.drawable.top25);
                mItem.setTitle(TOP25);
            }
            else{
                String[] appNames = new String[itemsList.size()];
                Bitmap[] appImages = new Bitmap[itemsList.size()];
                for(int i = 0; i<itemsList.size(); i++) {
                    appNames[i] = itemsList.get(i).name;
                    appImages[i] = itemsList.get(i).image.big_bm;
                }
                ItunesAdapter adapter = new ItunesAdapter(this,
                        appNames, appImages);
                setListAdapter(adapter);
                mItem.setIcon(R.drawable.mario_star);
                mItem.setTitle(FAV);
            }

        }
        return super.onOptionsItemSelected(mItem);
    }

    private void setupPrefs(){
        sharedpreferences = getSharedPreferences("MyFavorites", Context.MODE_PRIVATE);
        if(!sharedpreferences.contains(FAV)){
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putStringSet(FAV,new HashSet<String>());
            editor.commit();
        }
        getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v
                    ,int position, long id) {
                String appName = ((TextView)v.findViewById(R.id.txt)).getText().toString();
                ItunesItem selected = itemsMap.get(appName);
                if(selected == null) return false;
                SharedPreferences.Editor editor = sharedpreferences.edit();
                Set<String> prefValues = new HashSet<String>(sharedpreferences.getStringSet(FAV, null));
                if(prefValues.contains(selected.name)){
                    prefValues.remove(selected.name);
                    editor.putStringSet(FAV,prefValues);
                    editor.commit();
                    populateFavList(new ArrayList<String>(prefValues));
                    Toast.makeText(getApplicationContext(), selected.name + " removed from Favorites", Toast.LENGTH_LONG).show();
                }
                else {
                    prefValues.add(selected.name);
                    editor.putStringSet(FAV,prefValues);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), selected.name + " added to Favorites", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }

    private void populateFavList(ArrayList<String> prefValues){
        String[] appNames;
        Bitmap[] appImages;
        if(prefValues.isEmpty()){
            appNames = new String[1];
            appImages = new Bitmap[1];
            appNames[0] = "Long press an app to add or remove it from your favorites";
            int imageResource = getResources().getIdentifier("drawable/info", null, getPackageName());
            appImages[0] = drawableToBitmap(getResources().getDrawable(imageResource));
        }
        else {
            appNames = new String[prefValues.size()];
            appImages = new Bitmap[prefValues.size()];
            for (int i = 0; i < prefValues.size(); i++) {
                ItunesItem item = itemsMap.get(prefValues.get(i));
                if(item != null) {
                    appNames[i] = item.name;
                    appImages[i] = item.image.big_bm;
                }
            }
        }

        ItunesAdapter adapter = new ItunesAdapter(this,
                appNames, appImages);
        setListAdapter(adapter);
    }

    private void initialListSetup() {
        String[] appNames = new String[itemsList.size()];
        Bitmap[] appImages = new Bitmap[itemsList.size()];

        for(int i = 0; i<itemsList.size(); i++) {
            String name  = itemsList.get(i).name;
            appNames[i] = name;
            appImages[i] = itemsList.get(i).image.big_bm;
            itemsMap.put(name, itemsList.get(i));
        }
        ItunesAdapter adapter = new ItunesAdapter(this,
                appNames, appImages);
        setListAdapter(adapter);
        setupPrefs();
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}