package com.itunesstoreviewer.app;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.itunesstoreviewer.app.ItunesRssItemClasses.Entry;
import com.itunesstoreviewer.app.ItunesRssItemClasses.ItunesRSSResponse;
import com.itunesstoreviewer.app.ItunesRssItemClasses.LinkDeserializer;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.itunesstoreviewer.app.ItunesAppController.LOAD;


public class ItunesItemListFragment extends ListFragment {

    public static final String ARG_CAT_INDEX = "category_index";
    public static ListMode MODE;
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private static final String TAG = ItunesItemListFragment.class.getSimpleName();
    public enum ListMode {
        RSS, SEARCH};
    private Callbacks mCallbacks;
    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;
    private List<Entry> itunesItemList = new ArrayList<Entry>(LOAD);
    private ItunesAdapter adapter;
    private ItunesRSSResponse rssResponse;
    private CategoryAttribute catAttr;
    private int categoryIndex;

    public ItunesItemListFragment() {}

    public static ItunesItemListFragment newInstance(int categoryIndex, ListMode listMode) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_CAT_INDEX, categoryIndex);
        arguments.putSerializable(ListMode.class.getSimpleName(), listMode);
        ItunesItemListFragment fragment = new ItunesItemListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null
                && getArguments().containsKey(ARG_CAT_INDEX)
                && getArguments().containsKey(ListMode.class.getSimpleName())) {
            ListMode listMode = (ListMode) getArguments().getSerializable(ListMode.class.getSimpleName());
            switch (listMode){
                case RSS:
                    categoryIndex = getArguments().getInt(ARG_CAT_INDEX);
                    catAttr = ItunesAppController.getCategoryList().get(categoryIndex);
                    rssResponse = catAttr.getRssResponse();
                    itunesItemList.addAll(catAttr.getiTunesItems());
                    break;
                case SEARCH:
                    categoryIndex = getArguments().getInt(ARG_CAT_INDEX);
                    catAttr = ItunesAppController.getCategoryList().get(categoryIndex);
                    //TODO Use search response
                    rssResponse = catAttr.getRssResponse();
                    itunesItemList.addAll(catAttr.getiTunesItems());
                    break;
                default:
                    break;
            }

        }

        adapter = new ItunesAdapter(getActivity(), itunesItemList);
        setListAdapter(adapter);

        if (catAttr != null && itunesItemList.isEmpty()) {
            // Creating volley request obj if the savedInstanceState bundle is empty
            JsonObjectRequest jsonObjReq = getJsonObjectRequest();
            //try three times before error
            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    3,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            // Adding request to request queue
            ItunesAppController.getInstance().addToRequestQueue(jsonObjReq);
        }
    }

    private JsonObjectRequest getJsonObjectRequest() {
        return new JsonObjectRequest(catAttr.getUrl(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = LinkDeserializer.buildGson();
                        rssResponse = gson.fromJson(response.toString(), ItunesRSSResponse.class);
                        itunesItemList.clear();
                        itunesItemList.addAll(rssResponse.getFeed().getEntry());
                        if (isAdded())
                            setListShown(!itunesItemList.isEmpty());
                        catAttr.setRssResponse(rssResponse);
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.v(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "Error: " + error.getMessage());

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Log.d(TAG, TimeoutError.class.toString() + " or " + NoConnectionError.class.toString());
                    mCallbacks.networkError();
                } else if (error instanceof AuthFailureError) {
                    Log.d(TAG, AuthFailureError.class.toString());
                } else if (error instanceof ServerError) {
                    Log.d(TAG, ServerError.class.toString());
                } else if (error instanceof NetworkError) {
                    Log.d(TAG, NetworkError.class.toString());
                } else if (error instanceof ParseError) {
                    Log.d(TAG, ParseError.class.toString());
                }
            }
        });
    }

    @Override
    public void onResume() {
        if (itunesItemList.isEmpty())
            onCreate(null);
        setListShown(!itunesItemList.isEmpty());
        super.onResume();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListShown(!itunesItemList.isEmpty());
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
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        mCallbacks.onItunesItemSelected(ItunesAppController.getCategoryList().get(categoryIndex).getiTunesItems().get(position));
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
         * @param entry
         */
        public void onItunesItemSelected(Entry entry);

        public void networkError();
    }
}
