package com.appleappstorestop25.app;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;
import com.appleappstorestop25.app.SlidingTabs.SlidingTabsColorsFragment;


/**
 * An activity representing a list of ItunesItems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItunesItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItunesItemListFragment} and the item details
 * (if present) is a {@link ItunesItemDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link ItunesItemListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class ItunesItemListActivity extends FragmentActivity
        implements ItunesItemListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private ShareActionProvider mShareActionProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itunesitem_list);
        if (findViewById(R.id.itunesitem_detail_container) != null) {
            mTwoPane = true;
            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((ItunesItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.itunesitem_list))
                    .setActivateOnItemClick(true);

        }
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsColorsFragment fragment = new SlidingTabsColorsFragment();
            transaction.replace(R.id.sample_content_fragment, fragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mTwoPane) {
            // Inflate share_menu resource file.
            getMenuInflater().inflate(R.menu.share_menu, menu);

            // Locate MenuItem with ShareActionProvider
            MenuItem mItem = menu.findItem(R.id.menu_item_share);

            // Fetch and store ShareActionProvider
            mShareActionProvider = (ShareActionProvider) mItem.getActionProvider();

            // Return true to display share_menu
            return true;
        }
        else
            return super.onCreateOptionsMenu(menu);
    }

    // Call to update the share intent
    private void setShareIntent(Intent shareIntent) {
        if (mShareActionProvider != null) {
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }



    /**
     * Callback method from {@link ItunesItemListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     * @param item
     */
    @Override
    public void onItunesItemSelected(Entry item) {
        Log.d("DEBUG", "" + item);

        if (mTwoPane) {
            setShareIntent(item.generateShareIntent());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.itunesitem_detail_container, ItunesItemDetailFragment.newInstance(item))
                    .commit();
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, ItunesItemDetailActivity.class);
            detailIntent.putExtra(ItunesItemDetailFragment.ARG_ITEM_ID, item);
            startActivity(detailIntent);
        }
    }
}
