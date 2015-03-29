package com.appleappstorestop25.app;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;
import com.appleappstorestop25.app.ItunesItemClasses.LinkDeserializer;
import com.appleappstorestop25.app.SlidingTabs.SlidingTabsColorsFragment;
import com.google.gson.Gson;

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
public class ItunesItemListActivity extends FragmentActivity
        implements ItunesItemListFragment.Callbacks {

    public static final String ARG_ITEM_INDEX = "item_index";
    private final String mDrawerTitle = "Favorites";
    private final String USER_PREFS_FAV = "favorites";
    private final String SAVED_ITEM = "saved_item";
    Drawable unfavorite;
    Drawable favorite;
    MenuItem mFavButton;
    private ActionBar actionBar;
    private String mAppName;
    private String mTitle;
    private boolean mTwoPane;
    private ShareActionProvider mShareActionProvider;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Entry itunesItem;
    private ActionBarDrawerToggle mDrawerToggle;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunesitem_list);
        actionBar = getActionBar();
        gson = LinkDeserializer.buildGson();
        mTwoPane = getResources().getBoolean(R.bool.has_two_panes);
        mAppName = getResources().getString(R.string.app_name);
        mTitle = mAppName;
        favorite = getResources().getDrawable(R.drawable.ic_action_favorite_pink);
        unfavorite = getResources().getDrawable(R.drawable.ic_action_favorite);
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_ITEM)) {
            itunesItem = (Entry) savedInstanceState.getSerializable(SAVED_ITEM);
            mTitle = itunesItem.getImName().getLabel();
        }

        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        Set<String> restoredFav = prefs.getStringSet(USER_PREFS_FAV, null);
        if (restoredFav != null) {
            for (String s : restoredFav) {
                Entry itunesE = gson.fromJson(s, Entry.class);
                ItunesAppController.userFavorites.add(itunesE);
            }
        }
        setUpNavigationDrawer();

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsColorsFragment fragment = new SlidingTabsColorsFragment();
            transaction.replace(R.id.itunesitem_list, fragment);
            transaction.commit();
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

            boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
            menu.findItem(R.id.menu_item_share).setVisible(!drawerOpen);
            menu.findItem(R.id.fav_button).setVisible(!drawerOpen);
            menu.findItem(R.id.play_store_button).setVisible(!drawerOpen);

            return true;
        } else
            return super.onCreateOptionsMenu(menu);
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
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


    @Override
    public void onItunesItemSelected(int itemIndex, int categoryIndex) {
        Entry item = ItunesAppController.getCategoryList().get(categoryIndex).getiTunesItems().get(itemIndex);
        if (mTwoPane) {
            mTitle = item.getImName().getLabel();
            actionBar.setTitle(mTitle);
            if (itunesItem == null) toggleFavorite(item);
            else toggleFavorite(itunesItem, item);
            itunesItem = item;
            setShareIntent(ItunesAppController.generateShareIntent(itunesItem, mAppName));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.itunesitem_detail_container, ItunesItemDetailFragment.newInstance(itunesItem))
                    .commit();
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItunesItemDetailActivity.class);
            detailIntent.putExtra(ItunesItemDetailFragment.ARG_ITEM_ID, item);
            startActivity(detailIntent);
        }
    }

    @Override
    protected void onStop() {
        Set<String> favSet = new LinkedHashSet<String>();
        for (Entry e : ItunesAppController.userFavorites)
            favSet.add(gson.toJson(e));
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(USER_PREFS_FAV, favSet);
        editor.apply();
        super.onStop();
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

    private void toggleFavorite(Entry e1, Entry e2) {
        List<Entry> userFavorites = ItunesAppController.userFavorites;
        if (userFavorites.contains(e1) != userFavorites.contains(e2)) {
            toggleFavorite(e2);
        }
    }

    private void toggleFavorite(Entry e1) {
        if (ItunesAppController.userFavorites.contains(e1)) {
            mFavButton.setIcon(favorite);
        } else {
            mFavButton.setIcon(unfavorite);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (itunesItem != null) {
            // Serialize and persist the itunes item.
            outState.putSerializable(SAVED_ITEM, itunesItem);
        }
    }

    private void launchPlayStoreSearch() {
        String formattedName = itunesItem.getImName().getLabel();
        String searchCategory = ItunesAppController.getAppleToPlayStoreMap().get(itunesItem.getImContentType().getAttributes().getLabel());
        startActivity(new Intent(Intent.ACTION_VIEW
                , Uri.parse("https://play.google.com/store/search?q=" + formattedName + "&c=" + searchCategory)));
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            onItunesItemSelected(position, ItunesAppController.getCategoryList().size() - 1);
            mDrawerLayout.closeDrawers();
        }
    }
}
