package com.itunesstoreviewer.app;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import com.itunesstoreviewer.app.BaseClasses.ItunesItemActivity;

/**
 * An activity representing a single ItunesItem detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RSSActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ItunesItemDetailFragment}.
 */
public class ItunesItemDetailActivity extends ItunesItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunesitem_detail);
        if (getIntent() != null && getIntent().hasExtra(ItunesItemDetailFragment.ARG_ITEM_ID)) {
            itunesItem = (ItunesItem) getIntent().getSerializableExtra(ItunesItemDetailFragment.ARG_ITEM_ID);
            categoryAttribute = ItunesAppController.getCategoryAttribute(itunesItem);
            if (actionBar != null) {
                actionBar.setTitle(itunesItem.getItemName());
                actionBar.setBackgroundDrawable(new ColorDrawable(categoryAttribute.getColor()));
            }
        } else {
            Log.e(getLocalClassName(), "You cannot launch " +
                    getLocalClassName() + " without a valid value for " +
                    ItunesItemDetailFragment.ARG_ITEM_ID + " in the intent.");
            finish();
        }

        // Show the Up button in the action bar.
        actionBar.setDisplayHomeAsUpEnabled(true);

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
}
