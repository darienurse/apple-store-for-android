package com.example.rssreader;

/**
 * Created by Darien on 9/27/2014.
 */
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import java.util.*;

public class MainActivity extends Activity {
    private static List<ItunesItem> itemsList;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, ItunesListActivity.class);
        new RequestItemsServiceTask().execute();
    }

    public static List<ItunesItem> getItemsList() {
        return itemsList;
    }


    /**
     * populate list in background while showing progress dialog.
     */
    private class RequestItemsServiceTask
            extends AsyncTask<Void, Void, Void> {
        private ProgressDialog dialog =
                new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Please wait..");
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... unused) {
            ItemService itemService = new ItemService();
            try {
                itemsList = itemService.findAllItems();
            } catch (Throwable e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            finish();
        }
    }
}