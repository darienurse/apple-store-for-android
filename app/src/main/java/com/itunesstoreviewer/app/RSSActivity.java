package com.itunesstoreviewer.app;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import com.google.gson.Gson;
import com.itunesstoreviewer.app.BaseClasses.ItunesItemListActivity;
import com.itunesstoreviewer.app.ItunesRssItemClasses.Entry;
import com.itunesstoreviewer.app.ItunesRssItemClasses.LinkDeserializer;
import com.itunesstoreviewer.app.ItunesSearchItemClasses.Result;
import com.itunesstoreviewer.app.SlidingTabs.SlidingTabsColorsFragment;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * An activity representing a list of ItunesItems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItunesItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItunesItemListFragment} and the item details
 * (if present) is a {@link ItunesItemDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ItunesItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class RSSActivity extends ItunesItemListActivity {

    private final String mDrawerTitle = "Favorites";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunesitem_list);
        actionBar = getActionBar();
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mTwoPane = getResources().getBoolean(R.bool.has_two_panes);
        mAppName = getResources().getString(R.string.app_name);
        mTitle = mAppName;
        favorite = getResources().getDrawable(R.drawable.ic_action_favorite_pink);
        unfavorite = getResources().getDrawable(R.drawable.ic_action_favorite);
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_ITEM)) {
            itunesItem = (ItunesItem) savedInstanceState.getSerializable(SAVED_ITEM);
            mTitle = itunesItem.getTrackName();
        }

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        Set<String> restoredFav = prefs.getStringSet(USER_PREFS_FAV, null);
        if (restoredFav != null)
            System.out.println("ALL FAVS: " + restoredFav.size() + " ----- " + restoredFav);

        if (restoredFav != null) {
            for (String s : restoredFav) {
                ItunesItem itunesE = gson.fromJson(s, Entry.class);
                if (itunesE.getItemId() == null)
                    itunesE = gson.fromJson(s, Result.class);
                if (!ItunesAppController.userFavorites.contains(itunesE))
                    ItunesAppController.userFavorites.add(itunesE);
            }
        }
        setUpNavigationDrawer();

        if (savedInstanceState == null) {
            if (hasNetworkConnection()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                SlidingTabsColorsFragment slidingTabsColorsFragment = new SlidingTabsColorsFragment();
                transaction.replace(R.id.itunesitem_list, slidingTabsColorsFragment);
                transaction.commit();
            } else {
                networkError();
            }
        }
        Log.d("NURSE", "TwoPane enabled: " + mTwoPane);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mTwoPane) {
            getMenuInflater().inflate(R.menu.share_menu, menu);
            getMenuInflater().inflate(R.menu.details_menu, menu);

            MenuItem mItem = menu.findItem(R.id.menu_item_share);
            mFavButton = menu.findItem(R.id.fav_button);

            mShareActionProvider = (ShareActionProvider) mItem.getActionProvider();
            if (itunesItem != null) {
                if (ItunesAppController.userFavorites.contains(itunesItem)) {
                    mFavButton.setIcon(favorite);
                } else {
                    mFavButton.setIcon(unfavorite);
                }
            }

            boolean showMenuItems = !mDrawerLayout.isDrawerOpen(mDrawerList) && hasNetworkConnection();
            menu.findItem(R.id.menu_item_share).setVisible(showMenuItems);
            menu.findItem(R.id.fav_button).setVisible(showMenuItems);
            menu.findItem(R.id.play_store_button).setVisible(showMenuItems);
        }
        return super.onCreateOptionsMenu(menu);
    }






    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        ((DrawerAdapter) mDrawerList.getAdapter()).notifyDataSetChanged();
        invalidateOptionsMenu();
        super.onResume();
    }

    private void setUpNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new DrawerAdapter(this, ItunesAppController.userFavorites));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                actionBar.setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }



    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            onItunesItemSelected((ItunesItem) mDrawerList.getAdapter().getItem(position));
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.play_store_button:
                launchPlayStoreSearch();
                break;
            case R.id.fav_button:
                handleFavorite(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleFavorite(MenuItem item) {
        if (ItunesAppController.userFavorites.contains(itunesItem)) {
            ItunesAppController.userFavorites.remove(itunesItem);
            item.setIcon(unfavorite);
        } else {
            ItunesAppController.userFavorites.add(itunesItem);
            item.setIcon(favorite);
        }
        ((DrawerAdapter) mDrawerList.getAdapter()).notifyDataSetChanged();
    }
}
