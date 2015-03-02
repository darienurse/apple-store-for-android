package com.appleappstorestop25.app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.appleappstorestop25.app.ItunesItemClasses.Entry;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;

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
    private Entry itunesItem;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private FadingActionBarHelper mFadingHelper;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItunesItemDetailFragment() {
    }

    public static ItunesItemDetailFragment newInstance(int id) {
        Bundle arguments = new Bundle();
        arguments.putInt(ItunesItemDetailFragment.ARG_ITEM_ID, id);
        ItunesItemDetailFragment fragment = new ItunesItemDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            itunesItem = ItunesItemListFragment.getItunesItemList()
                    .get(getArguments().getInt(ItunesItemDetailFragment.ARG_ITEM_ID));
            getActivity().getActionBar().setTitle(itunesItem.getFormattedName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.fragment_itunesitem_detail, container, false);
        View rootView = mFadingHelper.createView(inflater);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        // Show the dummy content as text in a TextView.
        if (itunesItem != null) {
            ((TextView) rootView.findViewById(R.id.article_title)).setText(itunesItem.getFormattedName());
            ((TextView) rootView.findViewById(R.id.article_byline)).setText(itunesItem.getImArtist().getLabel());
            ((TextView) rootView.findViewById(R.id.article_body)).setText(itunesItem.getSummary().getLabel());
            ((NetworkImageView) rootView.findViewById(R.id.article_photo)).setImageUrl(itunesItem.getImImage().get(2).getLabel(), imageLoader);
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(R.color.primary_apple_blue)
                .headerLayout(R.layout.header_layout)
                .contentLayout(R.layout.fragment_itunesitem_detail)
                .parallax(false);
        mFadingHelper.initActionBar(activity);
    }


}
