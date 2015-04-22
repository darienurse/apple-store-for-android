package com.itunesstoreviewer.app;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.itunesstoreviewer.app.ItunesRssItemClasses.ItunesRSSResponse;
import com.itunesstoreviewer.app.ItunesRssItemClasses.LinkDeserializer;
import com.itunesstoreviewer.app.ItunesSearchItemClasses.ItunesSearchResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.itunesstoreviewer.app.ItunesAppController.LOAD;


public class ItunesItemListFragment extends ListFragment {

    public static final String ARG_CAT_INDEX = "category_index";
    public static final String ARG_QUERY = "item_query";
    public static ListMode MODE;
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private static final String TAG = "DEBUGZ";//ItunesItemListFragment.class.getSimpleName();
    public enum ListMode {
        RSS, SEARCH};
    private Callbacks mCallbacks;
    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;
    private List<ItunesItem> itunesItemList = new ArrayList<ItunesItem>(LOAD);
    private static Gson gson = LinkDeserializer.buildGson();
    private ItunesAdapter adapter;
    private ItunesRSSResponse rssResponse;
    private ItunesSearchResponse searchResponse;
    private CategoryAttribute catAttr;
    private int categoryIndex;
    private String query;

    public ItunesItemListFragment() {}

    public static ItunesItemListFragment newInstance(int categoryIndex) {
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_CAT_INDEX, categoryIndex);
        arguments.putSerializable(ListMode.class.getSimpleName(), ListMode.RSS);
        ItunesItemListFragment fragment = new ItunesItemListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public static ItunesItemListFragment newInstance(String query) {
        Bundle arguments = new Bundle();
        arguments.putString(ARG_QUERY, query);
        arguments.putSerializable(ListMode.class.getSimpleName(), ListMode.SEARCH);
        ItunesItemListFragment fragment = new ItunesItemListFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null
                && getArguments().containsKey(ListMode.class.getSimpleName())) {
            MODE = (ListMode) getArguments().getSerializable(ListMode.class.getSimpleName());
            switch (MODE){
                case RSS:
                    if(getArguments().containsKey(ARG_CAT_INDEX)) {
                        categoryIndex = getArguments().getInt(ARG_CAT_INDEX);
                        catAttr = ItunesAppController.getCategoryAttributeList().get(categoryIndex);
                        rssResponse = catAttr.getRssResponse();
                        itunesItemList.addAll(catAttr.getItunesItems());
                    }
                    break;
                case SEARCH:
                    if(getArguments().containsKey(ARG_QUERY)) {
                        query = getArguments().getString(ARG_QUERY);
                    }
                    break;
                default:
                    break;
            }

        }

        adapter = new ItunesAdapter(getActivity(), itunesItemList);
        setListAdapter(adapter);

        if ((catAttr != null && itunesItemList.isEmpty()) || MODE.equals(ListMode.SEARCH)) {
            // Creating volley request obj if the savedInstanceState bundle is empty
            JsonObjectRequest jsonObjReq;
            if(MODE.equals(ListMode.SEARCH)) {
                jsonObjReq = getJsonObjectRequest(query);
            }
            else{
                jsonObjReq = getJsonObjectRequest(catAttr.getUrl());
            }
            /*jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
            // Adding request to request queue
            ItunesAppController.getInstance().addToRequestQueue(jsonObjReq);
        }
    }

    private JsonObjectRequest getJsonObjectRequest(String url) {
        return new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        itunesItemList.clear();
                        new PopulateListViewTask().execute(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.v(TAG, "Error: " + error.getMessage());
                Log.d(TAG, "Error: " + error.getMessage());

                if (error instanceof TimeoutError) {
                    Log.d(TAG, TimeoutError.class.toString());
                    if(getActivity()!=null)
                        Toast.makeText(getActivity(), "Timeout", Toast.LENGTH_LONG).show();
                    mCallbacks.networkError();
                } else if (error instanceof NoConnectionError) {
                    Log.d(TAG, NetworkError.class.toString());
                    if(getActivity()!=null)
                        Toast.makeText(getActivity(), "No Network", Toast.LENGTH_LONG).show();
                    //mCallbacks.networkError();
                } else if (error instanceof AuthFailureError) {
                    Log.d(TAG, AuthFailureError.class.toString());
                } else if (error instanceof ServerError) {
                    Log.d(TAG, ServerError.class.toString());
                } else if (error instanceof NetworkError) {
                    Log.d(TAG, NetworkError.class.toString());
                } else if (error instanceof ParseError) {
                    Log.d(TAG, ParseError.class.toString());
                    onCreate(getArguments());
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
        mCallbacks.onItunesItemSelected(ItunesAppController.getCategoryAttributeList()
                .get(categoryIndex).getItunesItems().get(position));
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


    private class PopulateListViewTask extends AsyncTask<JSONObject, Void, Void> {
        protected Void doInBackground(JSONObject... params) {
            JSONObject response = params[0];
            switch (MODE) {
                case RSS:
                    rssResponse = gson.fromJson(response.toString(), ItunesRSSResponse.class);
                    break;
                case SEARCH:
                    searchResponse = gson.fromJson(response.toString(), ItunesSearchResponse.class);
                    break;
                default:
                    break;
            }
            return null;
        }

        protected void onPostExecute(Void v) {
            if(rssResponse!=null){
                itunesItemList.addAll(rssResponse.getFeed().getEntry());
                catAttr.setRssResponse(rssResponse);
            }else{
                itunesItemList.addAll(searchResponse.getResults());
            }
            adapter.notifyDataSetChanged();
            if (isAdded()) setListShown(!itunesItemList.isEmpty());
        }
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
        public void onItunesItemSelected(ItunesItem entry);

        public void networkError();
    }
}
