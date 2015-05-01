package com.itunesstoreviewer.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.itunesstoreviewer.app.BaseClasses.ItunesItemActivity;
import com.itunesstoreviewer.app.ItunesRssItemClasses.Entry;
import com.itunesstoreviewer.app.ItunesSearchItemClasses.Result;
import com.itunesstoreviewer.app.SlidingTabs.SlidingTabsColorsFragment;
import org.json.JSONArray;
import org.json.JSONException;


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
public class RSSActivity extends ItunesItemActivity {

    private final String mDrawerTitle = "Favorites";
    private final String SLIDING_FRAGMENT_ID = "SLIDING_FRAGMENT";

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunesitem_list);

        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_ITEM)) {
            itunesItem = (ItunesItem) savedInstanceState.getSerializable(SAVED_ITEM);
            mTitle = itunesItem.getItemName();
        }

        try {
            SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
            JSONArray jsonArray = new JSONArray(prefs.getString(
                    USER_PREFS_FAV, null));
                for (int i =0; i<jsonArray.length();i++) {
                    ItunesItem itunesE = gson.fromJson(jsonArray.getString(i), Entry.class);
                    if (itunesE.getItemId() == null)
                        itunesE = gson.fromJson(jsonArray.getString(i), Result.class);
                    if (!ItunesAppController.userFavorites.contains(itunesE))
                        ItunesAppController.userFavorites.add(itunesE);
                }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        setUpNavigationDrawer();

        if (savedInstanceState == null) {
            if (hasNetworkConnection()) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                SlidingTabsColorsFragment slidingTabsColorsFragment = new SlidingTabsColorsFragment();
                transaction.replace(R.id.itunesitem_list, slidingTabsColorsFragment, SLIDING_FRAGMENT_ID);
                transaction.commit();
            } else {
                networkError();
            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (mTwoPane) {
            boolean showMenuItems = !mDrawerLayout.isDrawerOpen(mDrawerList);
            menu.findItem(R.id.menu_item_share).setVisible(showMenuItems);
            menu.findItem(R.id.fav_button).setVisible(showMenuItems);
            menu.findItem(R.id.play_store_button).setVisible(showMenuItems);
        }
        return true;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void refresh() {
        SlidingTabsColorsFragment slidingTabsColorsFragment =
                (SlidingTabsColorsFragment)getSupportFragmentManager().findFragmentByTag(SLIDING_FRAGMENT_ID);
        if(slidingTabsColorsFragment!=null)
            slidingTabsColorsFragment.refresh();
        super.refresh();
    }

    @Override
    protected void handleFavorite(MenuItem item) {
        super.handleFavorite(item);
        ((DrawerAdapter) mDrawerList.getAdapter()).notifyDataSetChanged();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            onItunesItemSelected((ItunesItem) mDrawerList.getAdapter().getItem(position));
            mDrawerLayout.closeDrawers();
        }
    }
}
