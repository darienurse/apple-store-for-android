package com.itunesstoreviewer.app;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.itunesstoreviewer.app.ItunesRssItemClasses.Deserializer;
import com.itunesstoreviewer.app.ItunesRssItemClasses.ItunesRSSResponse;
import com.itunesstoreviewer.app.ItunesSearchItemClasses.ItunesSearchResponse;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.itunesstoreviewer.app.CategoryAttribute.LOAD;


public class ItunesItemListFragment extends ListFragment {

    public static final String ARG_CAT_INDEX = "category_index";
    public static final String ARG_QUERY = "item_query";
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    private static final String TAG = "DEBUG";//ItunesItemListFragment.class.getSimpleName();
    public static ListMode MODE;
    private static Gson gson = Deserializer.buildGson();
    private ProgressBar mLoadingView;
    private LinearLayout mListContainer;
    private JsonObjectRequest jsonObjReq;

    ;
    private Callbacks mCallbacks;
    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;
    private List<ItunesItem> itunesItemList = new ArrayList<ItunesItem>(LOAD);
    private ItunesAdapter adapter;
    private ItunesRSSResponse rssResponse;
    private ItunesSearchResponse searchResponse;
    private CategoryAttribute categoryAttribute;
    private String query;

    public ItunesItemListFragment() {
    }

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
            switch (MODE) {
                case RSS:
                    if (getArguments().containsKey(ARG_CAT_INDEX)) {
                        int categoryIndex = getArguments().getInt(ARG_CAT_INDEX);
                        categoryAttribute = ItunesAppController.getCategoryAttributeList().get(categoryIndex);
                        rssResponse = categoryAttribute.getRssResponse();
                        itunesItemList.addAll(categoryAttribute.getItunesItems());
                    }
                    break;
                case SEARCH:
                    if (getArguments().containsKey(ARG_QUERY)) {
                        query = getArguments().getString(ARG_QUERY);
                    }
                    break;
                default:
                    break;
            }

        }

        adapter = new ItunesAdapter(getActivity(), itunesItemList);
        setListAdapter(adapter);

        if ((categoryAttribute != null && itunesItemList.isEmpty()) || MODE.equals(ListMode.SEARCH)) {
            // Creating volley request obj if the savedInstanceState bundle is empty
            if (MODE.equals(ListMode.SEARCH)) {
                jsonObjReq = getJsonObjectRequest(query);
            } else {
                jsonObjReq = getJsonObjectRequest(categoryAttribute.getUrl());
            }
            ItunesAppController.getInstance().addToRequestQueue(jsonObjReq);
        }
    }

    private JsonObjectRequest getJsonObjectRequest(String url) {
        return new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        new PopulateListViewTask().execute(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.v(TAG, "Error: " + error.getMessage());
                if (error instanceof ParseError) {
                    //try again on parseError
                    onCreate(getArguments());
                }
            }
        });
    }

    @Override
    public void onResume() {
        if (itunesItemList.isEmpty())
            onCreate(null);
        setLoadingViewVisible(itunesItemList.isEmpty());
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (jsonObjReq != null)
            jsonObjReq.cancel();
        //ItunesAppController.getInstance().cancelPendingRequests(ItunesAppController.TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, null);
        mLoadingView = (ProgressBar) v.findViewById(R.id.loading);
        mListContainer = (LinearLayout) v.findViewById(R.id.listcontainer);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLoadingViewVisible(itunesItemList.isEmpty());
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
        //mCallbacks.onItunesItemSelected(ItunesAppController.getCategoryAttributeList()
        //        .get(categoryIndex).getItunesItems().get(position));
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

    private void setLoadingViewVisible(boolean visible) {
        if (null != mLoadingView && null != mListContainer) {
            mListContainer.setVisibility(visible ? View.GONE : View.VISIBLE);
            mLoadingView.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    public void refreshList() {
        setLoadingViewVisible(true);
        categoryAttribute.clearItunesItems();
        itunesItemList.clear();
        onCreate(getArguments());
    }

    public enum ListMode {RSS, SEARCH}


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
            itunesItemList.clear();
            if (rssResponse != null && rssResponse.getFeed() != null) {
                itunesItemList.addAll(rssResponse.getFeed().getEntry());
                categoryAttribute.setRssResponse(rssResponse);
            } else if (searchResponse != null) {
                itunesItemList.addAll(searchResponse.getResults());
            }
            adapter.notifyDataSetChanged();
            setLoadingViewVisible(false);
        }
    }
}
