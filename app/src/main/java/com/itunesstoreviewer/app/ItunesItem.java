package com.itunesstoreviewer.app;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Darien on 4/18/2015.
 */
public interface ItunesItem extends Serializable {

    String getTrackId();

    String getArtistName();

    String getTrackName();

    String getTrackViewUrl();

    String getArtworkUrl();

    String getItemPrice();

    String getItemRentalPrice();

    String getReleaseDate();

    String getPrimaryGenreName();

    String getItemSummary();

    String getCopyright();

    String getSellerName();

    List<String> getScreenshotUrls();

    String getArtworkUrlHD();

    String getCollectionName();

    String getPublisher();

    String getKind();
}
