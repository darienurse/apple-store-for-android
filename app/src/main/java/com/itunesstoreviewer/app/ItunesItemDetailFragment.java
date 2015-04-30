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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private ItunesItem itunesItem;
    private NetworkImageView networkImageView;
    private SliderLayout imageSliderView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItunesItemDetailFragment() {
    }

    public static ItunesItemDetailFragment newInstance(ItunesItem item) {
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
            itunesItem = (ItunesItem) getArguments().getSerializable(ItunesItemDetailFragment.ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.itunesitem_detail, container, false);
        TextView titleTextView = (TextView) rootView.findViewById(R.id.article_title);
        TextView byLineTextView = (TextView) rootView.findViewById(R.id.article_byline);
        TextView bodyTextView = (TextView) rootView.findViewById(R.id.article_body);
        networkImageView = ((NetworkImageView) rootView.findViewById(R.id.article_photo));
        imageSliderView = (SliderLayout) rootView.findViewById(R.id.slider);

        if (imageLoader == null) {
            imageLoader = ItunesAppController.getInstance().getImageLoader();
        }

        if (itunesItem != null) {
            String mName = itunesItem.getTrackName() != null ? itunesItem.getTrackName() : itunesItem.getCollectionName();
            titleTextView.setText(mName);
            byLineTextView.setText(itunesItem.getArtistName());
            String summary = generateSummary(itunesItem);
            bodyTextView.setText(summary);
            networkImageView.setImageUrl(itunesItem.getArtworkUrl(), imageLoader);
            new ImageExtractor().execute(itunesItem.getTrackViewUrl());
        }
        return rootView;
    }

    private String generateSummary(ItunesItem itunesItem) {
        String itemDate = null;
        try {
            Date date = itunesItem.getDateFormat().parse(itunesItem.getReleaseDate());
            SimpleDateFormat dt2 = new SimpleDateFormat("MMMM d, yyyy");
            itemDate = dt2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringBuilder summary = new StringBuilder();
        summary.append(getSummaryElement("Price", itunesItem.getItemPrice()));
        summary.append(getSummaryElement("Rental Price", itunesItem.getItemRentalPrice()));
        if (!itunesItem.getContentType().equals("podcast")) {
            if (itunesItem.getContentType().equals("TV Episode") || itunesItem.getContentType().equals("tv-episode"))
                summary.append(getSummaryElement("Season", itunesItem.getCollectionName()));
            else
                summary.append(getSummaryElement("Album", itunesItem.getCollectionName()));
        }
        summary.append(getSummaryElement("", "\n" + itunesItem.getItemSummary()));
        summary.append("\n");
        summary.append(getSummaryElement("Genre", itunesItem.getItemGenre()));
        summary.append(getSummaryElement("Release Date", itemDate));
        summary.append(getSummaryElement("", itunesItem.getCopyright()));
        summary.append(getSummaryElement("Publisher", itunesItem.getPublisher()));
        summary.append(getSummaryElement("Seller", itunesItem.getSellerName()));
        return summary.toString().trim();
    }


    private String getSummaryElement(String title, String detail) {
        if (detail != null && !detail.trim().equals("null")) {
            if (title.equals("")) return detail + "\n";
            else return title + ": " + detail + "\n";
        } else {
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