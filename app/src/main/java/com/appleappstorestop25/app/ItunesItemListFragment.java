package com.appleappstorestop25.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;
import com.appleappstorestop25.app.ItunesItemClasses.ItunesRSSResponse;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.appleappstorestop25.app.ItunesAppController.LOAD;


public class ItunesItemListFragment extends ListFragment {

    public static final String ARG_ATTRI_ID = "url_link_id";
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private static final String TAG = ItunesItemListFragment.class.getSimpleName();
    private static Callbacks sItunesCallbacks = new Callbacks() {
        @Override
        public void onItunesItemSelected(Entry item) {
        }
    };
    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sItunesCallbacks;
    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;
    private List<Entry> itunesItemList;
    private ProgressDialog pDialog;
    private ItunesAdapter adapter;
    private ItunesRSSResponse rssResponse;
    private CategoryAttribute catAttr;

    public ItunesItemListFragment() {
    }

    public static ItunesItemListFragment newInstance(CategoryAttribute ca) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ARG_ATTRI_ID, ca);
        ItunesItemListFragment fragment = new ItunesItemListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itunesItemList = new ArrayList<Entry>(LOAD);
        if (getArguments() != null && getArguments().containsKey(ARG_ATTRI_ID)) {
            catAttr = (CategoryAttribute) getArguments().getSerializable(ARG_ATTRI_ID);
            if(catAttr.getRssResponse() != null)
                itunesItemList.addAll(rssResponse.getFeed().getEntry());
        }

        adapter = new ItunesAdapter(getActivity(), itunesItemList);
        setListAdapter(adapter);

        pDialog = new ProgressDialog(this.getActivity(),R.style.AppTheme);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading... " + catAttr.getTitle());

        // 1 second delay before showing the loading screen. If the network is strong, the loading wont show.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (pDialog != null) pDialog.show();
            }
        }, 1000);

        // Creating volley request obj if the savedInstanceState bundle is empty
        if (catAttr !=null && itunesItemList.isEmpty()) {
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(catAttr.getUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, response.toString());
                            Gson gson = new Gson();
                            rssResponse = gson.fromJson(response.toString(), ItunesRSSResponse.class);
                            Log.d(TAG, rssResponse.getFeed().getAuthor().getName().getLabel());
                            itunesItemList.clear();
                            itunesItemList.addAll(rssResponse.getFeed().getEntry());
                            catAttr.setRssResponse(rssResponse);
                            Log.d(TAG, "" + adapter.getCount());
                            hidePDialog();

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "" + adapter.getCount());
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hidePDialog();

                }
            });

            // Adding request to request queue
            ItunesAppController.getInstance().addToRequestQueue(jsonObjReq);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(android.R.color.darker_gray));
        getListView().setDividerHeight(16);
        setActivateOnItemClick(true);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sItunesCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mCallbacks.onItunesItemSelected(itunesItemList.get(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         *
         * @param item
         */
        public void onItunesItemSelected(Entry item);
    }
}
