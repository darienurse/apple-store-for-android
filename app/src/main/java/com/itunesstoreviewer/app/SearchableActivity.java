package com.itunesstoreviewer.app;

import android.support.v4.app.FragmentActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class SearchableActivity extends FragmentActivity implements ItunesItemListFragment.Callbacks, AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private static final Map<String, String> map = new HashMap<String, String>();
    static{
            map.put("Apps", "iPadSoftware");
            map.put("Mac Apps", "macSoftware");
            map.put("Songs", "song");
            map.put("Albums", "album");
            map.put("Movies", "movie");
            map.put("TV Episodes", "tvEpisode");
            map.put("Books", "ebook");
            map.put("Podcasts", "podcast");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, ItunesItemListActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            try {
                query = URLEncoder.encode(query, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                finish();
            }
            String queryURL = "https://itunes.apple.com/search?term=" + query +
                    "&entity=" + map.get(spinner.getSelectedItem().toString());
            //use the query to search_menu your data somehow
            ItunesItemListFragment fragment = ItunesItemListFragment.newInstance(queryURL);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.itunesitem_list, fragment)
                    .commit();
        }
    }

    @Override
    public void onItunesItemSelected(ItunesItem entry) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        handleIntent(getIntent());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
