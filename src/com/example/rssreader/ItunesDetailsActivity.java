package com.example.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * Created by Darien on 11/8/2014.
 */
public class ItunesDetailsActivity extends Activity{
    private ShareActionProvider mShareActionProvider;
    private ItunesItem item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Intent i = getIntent();
        setContentView(R.layout.details);
        item = i.getParcelableExtra("item");
        TextView nameView = (TextView) findViewById(R.id.nameView);
        TextView artistView = (TextView) findViewById(R.id.artistView);
        TextView rightsView = (TextView) findViewById(R.id.rightsView);
        TextView releaseView = (TextView) findViewById(R.id.releaseView);
        TextView summaryView = (TextView) findViewById(R.id.summaryView);
        ImageView appImageView = (ImageView) findViewById(R.id.appImageView);
        nameView.setText(item.name);
        artistView.setText(item.artist);
        rightsView.setText(item.rights);
        releaseView.setText("Release Date: " + item.releaseDate);
        summaryView.setText(item.summary);
        appImageView.setImageBitmap(item.image.big_bm);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate share_menu resource file.
        getMenuInflater().inflate(R.menu.share_menu, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem mItem = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
        mShareActionProvider = (ShareActionProvider) mItem.getActionProvider();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing App Information");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Checkout this app: " + item.name
                + "\n" + item.link + "\n" + "#Hackerati");
        sendIntent.setType("text/plain");
        setShareIntent(sendIntent);

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
