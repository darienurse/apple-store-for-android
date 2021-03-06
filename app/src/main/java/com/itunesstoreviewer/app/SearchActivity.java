package com.itunesstoreviewer.app;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.itunesstoreviewer.app.BaseClasses.ItunesItemActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class SearchActivity extends ItunesItemActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Map<String, String> map;
    private View spinner_frame;
    private ActionBar mActionBar;
    private ColorDrawable colorDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        colorDrawable = new ColorDrawable();
        colorDrawable.setColor(ItunesAppController.getCategoryAttributeList().get(0).getColor());
        mActionBar = getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        map = new HashMap<String, String>();
        String[] categories = getResources().getStringArray(R.array.itunes_categories);
        String[] entities = getResources().getStringArray(R.array.itunes_entities);
        for (int i = 0; i < categories.length; i++)
            map.put(categories[i], entities[i]);
        spinner_frame = findViewById(R.id.spinner_frame);
        spinner_frame.setBackground(colorDrawable);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.itunes_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //AdView mAdView = (AdView) findViewById(R.id.adView);
        //AdRequest adRequest = new AdRequest.Builder().build();
        //mAdView.loadAd(adRequest);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            invalidateOptionsMenu();
            String query = intent.getStringExtra(SearchManager.QUERY);
            mActionBar.setTitle(getResources().getString(R.string.title_activity_searchable) + " " + query);
            try {
                query = URLEncoder.encode(query, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                finish();
            }
            String queryURL = "https://itunes.apple.com/search?term=" + query +
                    "&entity=" + map.get(spinner.getSelectedItem().toString()) + "&limit=49";
            //use the query to search_menu your data somehow
            ItunesItemListFragment fragment = ItunesItemListFragment.newInstance(queryURL);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.itunesitem_list, fragment)
                    .commit();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int color = ItunesAppController.getCategoryAttributeList().get(position).getColor();
        colorDrawable.setColor(color);
        mActionBar.setBackgroundDrawable(colorDrawable);
        spinner_frame.invalidate();
        handleIntent(getIntent());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

}
