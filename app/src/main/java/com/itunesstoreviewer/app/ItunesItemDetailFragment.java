package com.itunesstoreviewer.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.itunesstoreviewer.app.ItunesItemClasses.Entry;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
    private View rootView;
    private TextView titleTextView;
    private TextView byLineTextView;
    private TextView bodyTextView;
    private NetworkImageView networkImageView;
    private SliderLayout imageSliderView;

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
        rootView = inflater.inflate(R.layout.itunesitem_detail, container, false);
        titleTextView = (TextView) rootView.findViewById(R.id.article_title);
        byLineTextView = (TextView) rootView.findViewById(R.id.article_byline);
        bodyTextView = (TextView) rootView.findViewById(R.id.article_body);
        networkImageView = ((NetworkImageView) rootView.findViewById(R.id.article_photo));
        imageSliderView = (SliderLayout) rootView.findViewById(R.id.slider);

        if (imageLoader == null) {
            imageLoader = ItunesAppController.getInstance().getImageLoader();
        }

        // Show the dummy content as text in a TextView.
        if (itunesItem != null) {
            titleTextView.setText(itunesItem.getImName().getLabel());
            byLineTextView.setText(itunesItem.getImArtist().getLabel());
            String summary = generateSummary(itunesItem);
            //bodyTextView.setText(itunesItem.getSummary().getLabel());
            bodyTextView.setText(summary);
            networkImageView.setImageUrl(itunesItem.getImImage().get(2).getLabel(), imageLoader);
            new ImageExtractor().execute(itunesItem.getLink().get(0).getAttributes().getHref());
        }
        return rootView;
    }

    private String generateSummary(Entry itunesItem) {
        StringBuilder summary = new StringBuilder();
        summary.append(getPriceSafely(itunesItem));
        summary.append(getRentalPriceSafely(itunesItem));
        summary.append(getCollectionNameSafely(itunesItem));
        summary.append(getSummarySafely(itunesItem));
        summary.append(getCategorySafely(itunesItem));
        summary.append(getReleaseDateSafely(itunesItem));
        summary.append(getRightsSafely(itunesItem));
        summary.append(getPublisherSafely(itunesItem));
        summary.append(getVendorSafely(itunesItem));
        return summary.toString().trim();
    }

    private String getVendorSafely(Entry itunesItem) {
        try {
            return "Seller: " + itunesItem.getImVendorName().getLabel() + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getPublisherSafely(Entry itunesItem) {
        try {
            return "Publisher: " + itunesItem.getImPublisher().getLabel() + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getSummarySafely(Entry itunesItem) {
        try {
            String summary = itunesItem.getSummary().getLabel();
            summary = summary.replaceAll("&apos;", "'").replaceAll("&quot;", "\"");
            return "\n" + summary + "\n\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getRightsSafely(Entry itunesItem) {
        try {
            return itunesItem.getRights().getLabel() + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getReleaseDateSafely(Entry itunesItem) {
        try {
            return "Release Date: " + itunesItem.getImReleaseDate().getAttributes().getLabel() + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getCategorySafely(Entry itunesItem) {
        try {
            return "Genre: " + itunesItem.getCategory().getAttributes().getLabel() + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getCollectionNameSafely(Entry itunesItem) {
        try {
            return "Album: " + itunesItem.getImCollection().getImName().getLabel() + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getRentalPriceSafely(Entry itunesItem) {
        try {
            return "Rental Price: " + itunesItem.getImRentalPrice().getLabel() + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    private String getPriceSafely(Entry itunesItem) {
        try {
            String mPrice = itunesItem.getImPrice().getLabel().replaceFirst("-", "");
            mPrice = (mPrice.equals("Get")) ? "Free" : mPrice;
            return "Price: " + mPrice + "\n";
        } catch (NullPointerException e) {
            return "";
        }
    }

    @Override
    public void onStop() {
        imageSliderView.stopAutoCycle();
        super.onStop();
    }

    public class ImageExtractor extends AsyncTask<String, Void, List<String>> {

        protected List<String> doInBackground(String... urls) {
            try {
                InputStream input = new URL(urls[0]).openStream();
                Tidy tidy = new Tidy();
                tidy.setShowErrors(0);
                tidy.setQuiet(true);
                Document document = tidy.parseDOM(input, null);
                input.close();
                NodeList imgs = document.getElementsByTagName("img");
                NodeList logos = document.getElementsByTagName("meta");
                List<String> srcs = new ArrayList<String>();

                for (int i = 0; i < logos.getLength(); i++) {
                    try {
                        String property = logos.item(i).getAttributes().getNamedItem("property").getNodeValue();
                        String content = logos.item(i).getAttributes().getNamedItem("content").getNodeValue();
                        if (property.equals("og:image")) {
                            srcs.add(content);
                            break;
                        }
                    } catch (NullPointerException e) {
                        //Nothing is wrong here. The application is simply looking for the icon image in all the
                        //elements labeled 'meta'. NullPointer results when the element does not contain the image.
                    }
                }

                for (int i = 0; i < imgs.getLength(); i++) {
                    String src = imgs.item(i).getAttributes().getNamedItem("src").getNodeValue();
                    if (src.endsWith("jpeg")) srcs.add(src);
                }
                return srcs;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(List<String> results) {
            if (results == null || results.isEmpty()) return;
            networkImageView.setImageUrl(results.remove(0), imageLoader);
            if (!results.isEmpty()) {
                // initialize a SliderLayout for each image retrieved from the ImageExtractor
                for (String name : results) {
                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView
                            .image(name)
                            .setScaleType(BaseSliderView.ScaleType.CenterInside);
                    imageSliderView.addSlider(textSliderView);
                }
                imageSliderView.setPresetTransformer(SliderLayout.Transformer.Accordion);
                imageSliderView.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                imageSliderView.setCustomAnimation(new DescriptionAnimation());
                imageSliderView.setDuration(4000);
                imageSliderView.setVisibility(View.VISIBLE);
            }
        }
    }
}