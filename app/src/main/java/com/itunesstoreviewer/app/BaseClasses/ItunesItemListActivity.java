package com.itunesstoreviewer.app.BaseClasses;

import android.app.ActionBar;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.*;
import android.widget.SearchView;
import android.widget.ShareActionProvider;
import com.google.gson.Gson;
import com.itunesstoreviewer.app.*;
import com.itunesstoreviewer.app.ItunesRssItemClasses.LinkDeserializer;
import com.itunesstoreviewer.app.SlidingTabs.SlidingTabsColorsFragment;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Darien on 4/29/2015.
 */
public abstract class ItunesItemListActivity extends FragmentActivity implements ItunesItemListFragment.Callbacks {
    protected final String USER_PREFS_FAV = "favorites";
    protected final String SAVED_ITEM = "saved_item";
    protected final Gson gson = LinkDeserializer.buildGson();
    protected ActionBar actionBar;
    protected String mAppName;
    protected String mTitle;
    protected boolean mTwoPane;
    protected ShareActionProvider mShareActionProvider;
    protected ItunesItem itunesItem;
    protected ConnectivityManager connectivityManager;
    protected Drawable unfavorite;
    protected Drawable favorite;
    protected MenuItem mFavButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getActionBar();
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        mTwoPane = getResources().getBoolean(R.bool.has_two_panes);
        mAppName = getResources().getString(R.string.app_name);
        mTitle = mAppName;
        favorite = getResources().getDrawable(R.drawable.ic_action_favorite_pink);
        unfavorite = getResources().getDrawable(R.drawable.ic_action_favorite);
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
        }
        getMenuInflater().inflate(R.menu.search_menu, menu);
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        //searchView.setQueryRefinementEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }


    protected boolean hasNetworkConnection() {
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        invalidateOptionsMenu();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setQuery("", false);
        if (mTwoPane) {
            boolean showMenuItems = hasNetworkConnection() && itunesItem != null;
            menu.findItem(R.id.menu_item_share).setVisible(showMenuItems);
            menu.findItem(R.id.fav_button).setVisible(showMenuItems);
            menu.findItem(R.id.play_store_button).setVisible(showMenuItems);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    // Call to update the share intent
    protected void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }

    @Override
    public void networkError() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.itunesitem_list, new ErrorFragment());
        transaction.commit();
    }

    @Override
    protected void onStop() {
        Set<String> favSet = new LinkedHashSet<String>();
        for (ItunesItem e : ItunesAppController.userFavorites) {
            favSet.add(gson.toJson(e));
        }
        System.out.println("ALL FAVS: " + favSet.size() + " ----- " + favSet);
        SharedPreferences settings = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putStringSet(USER_PREFS_FAV, favSet);
        editor.apply();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (itunesItem != null) {
            // Serialize and persist the itunes item.
            outState.putSerializable(SAVED_ITEM, itunesItem);
        }
    }

    protected void launchPlayStoreSearch() {
        String formattedName = itunesItem.getTrackName();
        String searchCategory = ItunesAppController.getAppleToPlayStoreMap().get(itunesItem.getContentType());
        startActivity(new Intent(Intent.ACTION_VIEW
                , Uri.parse("https://play.google.com/store/search?q=" + formattedName + "&c=" + searchCategory)));
    }

    public void retryButtonClick(View v) {
        if (hasNetworkConnection()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsColorsFragment slidingTabsColorsFragment = new SlidingTabsColorsFragment();
            transaction.replace(R.id.itunesitem_list, slidingTabsColorsFragment);
            transaction.commit();
        } else {
            networkError();
        }
    }

    @Override
    public void onItunesItemSelected(ItunesItem item) {
        if (mTwoPane) {
            mTitle = item.getTrackName();
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
        invalidateOptionsMenu();
    }

    private void toggleFavorite(ItunesItem e1, ItunesItem e2) {
        List<ItunesItem> userFavorites = ItunesAppController.userFavorites;
        if (userFavorites.contains(e1) != userFavorites.contains(e2)) {
            toggleFavorite(e2);
        }
    }

    private void toggleFavorite(ItunesItem e1) {
        if (ItunesAppController.userFavorites.contains(e1)) {
            mFavButton.setIcon(favorite);
        } else {
            mFavButton.setIcon(unfavorite);
        }
    }

    protected void handleFavorite(MenuItem item) {
        if (ItunesAppController.userFavorites.contains(itunesItem)) {
            ItunesAppController.userFavorites.remove(itunesItem);
            item.setIcon(unfavorite);
        } else {
            ItunesAppController.userFavorites.add(itunesItem);
            item.setIcon(favorite);
        }
    }


    static public class ErrorFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.error_layout, container, false);
        }
    }


}
