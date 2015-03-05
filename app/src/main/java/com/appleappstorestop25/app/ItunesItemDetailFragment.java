package com.appleappstorestop25.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;

/**
 * A fragment representing a single ItunesItem detail screen.
 * This fragment is either contained in a {@link ItunesItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItunesItemDetailActivity}
 * on handsets.
 */
public class ItunesItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    ImageLoader imageLoader = ItunesAppController.getInstance().getImageLoader();
    private Entry itunesItem;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItunesItemDetailFragment() {
    }

    public static ItunesItemDetailFragment newInstance(Entry item) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(ItunesItemDetailFragment.ARG_ITEM_ID, item);
        ItunesItemDetailFragment fragment = new ItunesItemDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            itunesItem = (Entry) getArguments().getSerializable(ItunesItemDetailFragment.ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.itunesitem_detail, container, false);


        if (imageLoader == null)
            imageLoader = ItunesAppController.getInstance().getImageLoader();

        // Show the dummy content as text in a TextView.
        if (itunesItem != null) {
            ((TextView) rootView.findViewById(R.id.article_title)).setText(itunesItem.getFormattedName());
            ((TextView) rootView.findViewById(R.id.article_byline)).setText(itunesItem.getImArtist().getLabel());
            try {
                ((TextView) rootView.findViewById(R.id.article_body)).setText(itunesItem.getSummary().getLabel());
            } catch (NullPointerException e) {
                ((TextView) rootView.findViewById(R.id.article_body)).setText("No description available");
                Log.d("NURSE", e.toString());
            }
            ((NetworkImageView) rootView.findViewById(R.id.article_photo)).setImageUrl(itunesItem.getImImage().get(2).getLabel(), imageLoader);
        }

        return rootView;
    }

}
