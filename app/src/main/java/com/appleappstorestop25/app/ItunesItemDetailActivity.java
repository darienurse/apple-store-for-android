package com.appleappstorestop25.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;

/**
 * An activity representing a single ItunesItem detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItunesItemListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ItunesItemDetailFragment}.
 */
public class ItunesItemDetailActivity extends FragmentActivity {

    private ShareActionProvider mShareActionProvider;
    private Entry itunesItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunesitem_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putSerializable(ItunesItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getSerializableExtra(ItunesItemDetailFragment.ARG_ITEM_ID));
            ItunesItemDetailFragment fragment = new ItunesItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.itunesitem_detail_container, fragment)
                    .commit();
            if (arguments.containsKey(ItunesItemDetailFragment.ARG_ITEM_ID)) {
                itunesItem = (Entry) arguments.getSerializable(ItunesItemDetailFragment.ARG_ITEM_ID);
            }
        }
        else
        /*TODO There is an issue with ManuelPeinado's FadingActionBar where if the user changes the screen orientation,
        the application will crash. For now, the user will be bumped back to the listview on orientation change.*/
            finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button.
            NavUtils.navigateUpTo(this, new Intent(this, ItunesItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate share_menu resource file.
        getMenuInflater().inflate(R.menu.share_menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem mItem = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) mItem.getActionProvider();

        if(itunesItem!=null) setShareIntent(itunesItem.generateShareIntent());

        // Return true to display share_menu
        return true;
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
