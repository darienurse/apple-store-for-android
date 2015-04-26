package com.itunesstoreviewer.app;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Darien on 4/18/2015.
 */
public interface ItunesItem extends Serializable {

    String getItemId();

    String getArtistName();

    String getTrackName();

    String getTrackViewUrl();

    String getArtworkUrl();

    String getItemPrice();

    String getItemRentalPrice();

    String getReleaseDate();

    String getItemGenre();

    String getItemSummary();

    String getCopyright();

    String getSellerName();

    List<String> getScreenshotUrls();

    String getArtworkUrlHD();

    String getCollectionName();

    String getPublisher();

    String getContentType();

    SimpleDateFormat getDateFormat();
}
