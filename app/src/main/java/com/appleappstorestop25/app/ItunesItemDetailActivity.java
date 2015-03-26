package com.appleappstorestop25.app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;

/**
 * An activity representing a single ItunesItem detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItunesItemListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ItunesItemDetailFragment}.
 */
public class ItunesItemDetailActivity extends FragmentActivity {

    private ShareActionProvider mShareActionProvider;
    private Entry itunesItem;
    private Drawable favorite;
    private Drawable unfavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunesitem_detail);
        unfavorite = getResources().getDrawable(R.drawable.ic_action_favorite);
        favorite = getResources().getDrawable(R.drawable.ic_action_favorite_pink);
        if (getIntent() != null && getIntent().hasExtra(ItunesItemDetailFragment.ARG_ITEM_ID)) {
            itunesItem = (Entry) getIntent().getSerializableExtra(ItunesItemDetailFragment.ARG_ITEM_ID);
            getActionBar().setTitle(itunesItem.getImName().getLabel());
            //getActionBar().setBackgroundDrawable(ItunesAppController.globalColorController);
        }else{
            Log.e(getLocalClassName(), "You cannot launch "+
                    getLocalClassName()+" without a valid value for " +
                    ItunesItemDetailFragment.ARG_ITEM_ID + " in the intent.");
            finish();
        }

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            ItunesItemDetailFragment fragment = ItunesItemDetailFragment.newInstance(itunesItem);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.itunesitem_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        getMenuInflater().inflate(R.menu.details_menu, menu);
        MenuItem mItem = menu.findItem(R.id.menu_item_share);
        MenuItem mFavButton = menu.findItem(R.id.fav_button);
        mShareActionProvider = (ShareActionProvider) mItem.getActionProvider();
        setShareIntent(ItunesAppController.generateShareIntent(itunesItem, getResources().getString(R.string.app_name)));

        if (ItunesAppController.userFavorites.contains(itunesItem)) {
            mFavButton.setIcon(favorite);
        } else {
            mFavButton.setIcon(unfavorite);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, new Intent(this, ItunesItemListActivity.class));
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

    private void launchPlayStoreSearch() {
        String formattedName = itunesItem.getImName().getLabel();
        String searchCategory = ItunesAppController.getAppleToPlayStoreMap().get(itunesItem.getImContentType().getAttributes().getLabel());
        startActivity(new Intent(Intent.ACTION_VIEW
                , Uri.parse("https://play.google.com/store/search?q=" + formattedName + "&c=" + searchCategory)));
    }

    private void handleFavorite(MenuItem item) {
        if (ItunesAppController.userFavorites.contains(itunesItem)) {
            ItunesAppController.userFavorites.remove(itunesItem);
            item.setIcon(unfavorite);
        } else {
            ItunesAppController.userFavorites.add(itunesItem);
            item.setIcon(favorite);
        }
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
