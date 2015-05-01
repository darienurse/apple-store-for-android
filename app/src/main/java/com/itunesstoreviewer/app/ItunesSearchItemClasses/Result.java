package com.itunesstoreviewer.app.ItunesSearchItemClasses;

import com.google.gson.annotations.Expose;
import com.itunesstoreviewer.app.ItunesItem;
import org.apache.commons.lang.StringEscapeUtils;

import javax.annotation.Generated;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Result implements ItunesItem {

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private final static String COLLECTION = "collection";

    @Expose
    private String wrapperType;
    @Expose
    private String collectionType;
    @Expose
    private String kind;
    @Expose
    private Integer collectionId;
    @Expose
    private Integer trackId;
    @Expose
    private String artistName;
    @Expose
    private String collectionName;
    @Expose
    private String trackName;
    @Expose
    private String collectionCensoredName;
    @Expose
    private String trackCensoredName;
    @Expose
    private Integer collectionArtistId;
    @Expose
    private String collectionArtistViewUrl;
    @Expose
    private String collectionViewUrl;
    @Expose
    private String trackViewUrl;
    @Expose
    private String previewUrl;
    @Expose
    private String artworkUrl30;
    @Expose
    private String artworkUrl60;
    @Expose
    private String artworkUrl100;
    @Expose
    private Double collectionPrice;
    @Expose
    private Double trackPrice;
    @Expose
    private Double trackRentalPrice;
    @Expose
    private Double collectionHdPrice;
    @Expose
    private Double trackHdPrice;
    @Expose
    private Double trackHdRentalPrice;
    @Expose
    private String releaseDate;
    @Expose
    private String collectionExplicitness;
    @Expose
    private String trackExplicitness;
    @Expose
    private Integer discCount;
    @Expose
    private Integer discNumber;
    @Expose
    private Integer trackCount;
    @Expose
    private Integer trackNumber;
    @Expose
    private Integer trackTimeMillis;
    @Expose
    private String country;
    @Expose
    private String currency;
    @Expose
    private String primaryGenreName;
    @Expose
    private String contentAdvisoryRating;
    @Expose
    private String longDescription;
    @Expose
    private String radioStationUrl;
    @Expose
    private String feedUrl;
    @Expose
    private String artworkUrl600;
    @Expose
    private List<String> genreIds = new ArrayList<String>();
    @Expose
    private List<String> genres = new ArrayList<String>();
    @Expose
    private Integer artistId;
    @Expose
    private String artistViewUrl;
    @Expose
    private String copyright;
    @Expose
    private String description;
    @Expose
    private String shortDescription;
    @Expose
    private List<String> features = new ArrayList<String>();
    @Expose
    private List<String> supportedDevices = new ArrayList<String>();
    @Expose
    private List<Object> advisories = new ArrayList<Object>();
    @Expose
    private Boolean isGameCenterEnabled;
    @Expose
    private List<String> screenshotUrls = new ArrayList<String>();
    @Expose
    private List<Object> ipadScreenshotUrls = new ArrayList<Object>();
    @Expose
    private String artworkUrl512;
    @Expose
    private Double price;
    @Expose
    private String version;
    @Expose
    private String sellerName;
    @Expose
    private String bundleId;
    @Expose
    private Integer primaryGenreId;
    @Expose
    private String releaseNotes;
    @Expose
    private String minimumOsVersion;
    @Expose
    private String formattedPrice;
    @Expose
    private List<String> languageCodesISO2A = new ArrayList<String>();
    @Expose
    private Long fileSizeBytes;
    @Expose
    private Double averageUserRatingForCurrentVersion;
    @Expose
    private Integer userRatingCountForCurrentVersion;
    @Expose
    private String trackContentRating;
    @Expose
    private Double averageUserRating;
    @Expose
    private Integer userRatingCount;
    @Expose
    private List<Integer> artistIds = new ArrayList<Integer>();

    /**
     * @return The wrapperType
     */
    public String getWrapperType() {
        return wrapperType;
    }

    /**
     * @param wrapperType The wrapperType
     */
    public void setWrapperType(String wrapperType) {
        this.wrapperType = wrapperType;
    }

    /**
     * @return The collectionType
     */
    public String getCollectionType() {
        return collectionType;
    }

    /**
     * @param collectionType The collectionType
     */
    public void seCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    /**
     * @return The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return The collectionId
     */
    public Integer getCollectionId() {
        return collectionId;
    }

    /**
     * @param collectionId The collectionId
     */
    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    /**
     * @return The trackId
     */
    public Integer getTrackId() {
        return trackId;
    }

    /**
     * @param trackId The trackId
     */
    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    /**
     * @return The artistName
     */
    @Override
    public String getArtistName() {
        return artistName;
    }


    /**
     * @param artistName The artistName
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return The collectionName
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * @param collectionName The collectionName
     */
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * @return The trackName
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     * @param trackName The trackName
     */
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    /**
     * @return The collectionCensoredName
     */
    public String getCollectionCensoredName() {
        return collectionCensoredName;
    }

    /**
     * @param collectionCensoredName The collectionCensoredName
     */
    public void setCollectionCensoredName(String collectionCensoredName) {
        this.collectionCensoredName = collectionCensoredName;
    }

    /**
     * @return The trackCensoredName
     */
    public String getTrackCensoredName() {
        return trackCensoredName;
    }

    /**
     * @param trackCensoredName The trackCensoredName
     */
    public void setTrackCensoredName(String trackCensoredName) {
        this.trackCensoredName = trackCensoredName;
    }

    /**
     * @return The collectionArtistId
     */
    public Integer getCollectionArtistId() {
        return collectionArtistId;
    }

    /**
     * @param collectionArtistId The collectionArtistId
     */
    public void setCollectionArtistId(Integer collectionArtistId) {
        this.collectionArtistId = collectionArtistId;
    }

    /**
     * @return The collectionArtistViewUrl
     */
    public String getCollectionArtistViewUrl() {
        return collectionArtistViewUrl;
    }

    /**
     * @param collectionArtistViewUrl The collectionArtistViewUrl
     */
    public void setCollectionArtistViewUrl(String collectionArtistViewUrl) {
        this.collectionArtistViewUrl = collectionArtistViewUrl;
    }

    /**
     * @return The collectionViewUrl
     */
    public String getCollectionViewUrl() {
        return collectionViewUrl;
    }

    /**
     * @param collectionViewUrl The collectionViewUrl
     */
    public void setCollectionViewUrl(String collectionViewUrl) {
        this.collectionViewUrl = collectionViewUrl;
    }

    /**
     * @return The trackViewUrl
     */
    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    /**
     * @param trackViewUrl The trackViewUrl
     */
    public void setTrackViewUrl(String trackViewUrl) {
        this.trackViewUrl = trackViewUrl;
    }

    /**
     * @return The previewUrl
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * @param previewUrl The previewUrl
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * @return The artworkUrl30
     */
    public String getArtworkUrl30() {
        return artworkUrl30;
    }

    /**
     * @param artworkUrl30 The artworkUrl30
     */
    public void setArtworkUrl30(String artworkUrl30) {
        this.artworkUrl30 = artworkUrl30;
    }

    /**
     * @return The artworkUrl60
     */
    public String getArtworkUrl60() {
        return artworkUrl60;
    }

    /**
     * @param artworkUrl60 The artworkUrl60
     */
    public void setArtworkUrl60(String artworkUrl60) {
        this.artworkUrl60 = artworkUrl60;
    }

    /**
     * @return The artworkUrl100
     */
    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    /**
     * @param artworkUrl100 The artworkUrl100
     */
    public void setArtworkUrl100(String artworkUrl100) {
        this.artworkUrl100 = artworkUrl100;
    }

    /**
     * @return The collectionPrice
     */
    public Double getCollectionPrice() {
        return collectionPrice;
    }

    /**
     * @param collectionPrice The collectionPrice
     */
    public void setCollectionPrice(Double collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    /**
     * @return The trackPrice
     */
    public Double getTrackPrice() {
        return trackPrice;
    }

    /**
     * @param trackPrice The trackPrice
     */
    public void setTrackPrice(Double trackPrice) {
        this.trackPrice = trackPrice;
    }

    /**
     * @return The trackRentalPrice
     */
    public Double getTrackRentalPrice() {
        return trackRentalPrice;
    }

    /**
     * @param trackRentalPrice The trackRentalPrice
     */
    public void setTrackRentalPrice(Double trackRentalPrice) {
        this.trackRentalPrice = trackRentalPrice;
    }

    /**
     * @return The collectionHdPrice
     */
    public Double getCollectionHdPrice() {
        return collectionHdPrice;
    }

    /**
     * @param collectionHdPrice The collectionHdPrice
     */
    public void setCollectionHdPrice(Double collectionHdPrice) {
        this.collectionHdPrice = collectionHdPrice;
    }

    /**
     * @return The trackHdPrice
     */
    public Double getTrackHdPrice() {
        return trackHdPrice;
    }

    /**
     * @param trackHdPrice The trackHdPrice
     */
    public void setTrackHdPrice(Double trackHdPrice) {
        this.trackHdPrice = trackHdPrice;
    }

    /**
     * @return The trackHdRentalPrice
     */
    public Double getTrackHdRentalPrice() {
        return trackHdRentalPrice;
    }

    /**
     * @param trackHdRentalPrice The trackHdRentalPrice
     */
    public void setTrackHdRentalPrice(Double trackHdRentalPrice) {
        this.trackHdRentalPrice = trackHdRentalPrice;
    }

    /**
     * @return The releaseDate
     */
    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate The releaseDate
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return The collectionExplicitness
     */
    public String getCollectionExplicitness() {
        return collectionExplicitness;
    }

    /**
     * @param collectionExplicitness The collectionExplicitness
     */
    public void setCollectionExplicitness(String collectionExplicitness) {
        this.collectionExplicitness = collectionExplicitness;
    }

    /**
     * @return The trackExplicitness
     */
    public String getTrackExplicitness() {
        return trackExplicitness;
    }

    /**
     * @param trackExplicitness The trackExplicitness
     */
    public void setTrackExplicitness(String trackExplicitness) {
        this.trackExplicitness = trackExplicitness;
    }

    /**
     * @return The discCount
     */
    public Integer getDiscCount() {
        return discCount;
    }

    /**
     * @param discCount The discCount
     */
    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }

    /**
     * @return The discNumber
     */
    public Integer getDiscNumber() {
        return discNumber;
    }

    /**
     * @param discNumber The discNumber
     */
    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

    /**
     * @return The trackCount
     */
    public Integer getTrackCount() {
        return trackCount;
    }

    /**
     * @param trackCount The trackCount
     */
    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

    /**
     * @return The trackNumber
     */
    public Integer getTrackNumber() {
        return trackNumber;
    }

    /**
     * @param trackNumber The trackNumber
     */
    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * @return The trackTimeMillis
     */
    public Integer getTrackTimeMillis() {
        return trackTimeMillis;
    }

    /**
     * @param trackTimeMillis The trackTimeMillis
     */
    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.trackTimeMillis = trackTimeMillis;
    }

    /**
     * @return The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return The primaryGenreName
     */
    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    /**
     * @param primaryGenreName The primaryGenreName
     */
    public void setPrimaryGenreName(String primaryGenreName) {
        this.primaryGenreName = primaryGenreName;
    }

    /**
     * @return The contentAdvisoryRating
     */
    public String getContentAdvisoryRating() {
        return contentAdvisoryRating;
    }

    /**
     * @param contentAdvisoryRating The contentAdvisoryRating
     */
    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.contentAdvisoryRating = contentAdvisoryRating;
    }

    /**
     * @return The longDescription
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * @param longDescription The longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * @return The radioStationUrl
     */
    public String getRadioStationUrl() {
        return radioStationUrl;
    }

    /**
     * @param radioStationUrl The radioStationUrl
     */
    public void setRadioStationUrl(String radioStationUrl) {
        this.radioStationUrl = radioStationUrl;
    }

    /**
     * @return The feedUrl
     */
    public String getFeedUrl() {
        return feedUrl;
    }

    /**
     * @param feedUrl The feedUrl
     */
    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    /**
     * @return The artworkUrl600
     */
    public String getArtworkUrl600() {
        return artworkUrl600;
    }

    /**
     * @param artworkUrl600 The artworkUrl600
     */
    public void setArtworkUrl600(String artworkUrl600) {
        this.artworkUrl600 = artworkUrl600;
    }

    /**
     * @return The genreIds
     */
    public List<String> getGenreIds() {
        return genreIds;
    }

    /**
     * @param genreIds The genreIds
     */
    public void setGenreIds(List<String> genreIds) {
        this.genreIds = genreIds;
    }

    /**
     * @return The genres
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * @param genres The genres
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * @return The artistId
     */
    public Integer getArtistId() {
        return artistId;
    }

    /**
     * @param artistId The artistId
     */
    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    /**
     * @return The artistViewUrl
     */
    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    /**
     * @param artistViewUrl The artistViewUrl
     */
    public void setArtistViewUrl(String artistViewUrl) {
        this.artistViewUrl = artistViewUrl;
    }

    /**
     * @return The copyright
     */
    @Override
    public String getCopyright() {
        return copyright;
    }

    /**
     * @param copyright The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The shortDescription
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @param shortDescription The shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * @return The features
     */
    public List<String> getFeatures() {
        return features;
    }

    /**
     * @param features The features
     */
    public void setFeatures(List<String> features) {
        this.features = features;
    }

    /**
     * @return The supportedDevices
     */
    public List<String> getSupportedDevices() {
        return supportedDevices;
    }

    /**
     * @param supportedDevices The supportedDevices
     */
    public void setSupportedDevices(List<String> supportedDevices) {
        this.supportedDevices = supportedDevices;
    }

    /**
     * @return The advisories
     */
    public List<Object> getAdvisories() {
        return advisories;
    }

    /**
     * @param advisories The advisories
     */
    public void setAdvisories(List<Object> advisories) {
        this.advisories = advisories;
    }

    /**
     * @return The isGameCenterEnabled
     */
    public Boolean getIsGameCenterEnabled() {
        return isGameCenterEnabled;
    }

    /**
     * @param isGameCenterEnabled The isGameCenterEnabled
     */
    public void setIsGameCenterEnabled(Boolean isGameCenterEnabled) {
        this.isGameCenterEnabled = isGameCenterEnabled;
    }

    /**
     * @return The screenshotUrls
     */
    public List<String> getScreenshotUrls() {
        return screenshotUrls;
    }

    /**
     * @param screenshotUrls The screenshotUrls
     */
    public void setScreenshotUrls(List<String> screenshotUrls) {
        this.screenshotUrls = screenshotUrls;
    }

    /**
     * @return The ipadScreenshotUrls
     */
    public List<Object> getIpadScreenshotUrls() {
        return ipadScreenshotUrls;
    }

    /**
     * @param ipadScreenshotUrls The ipadScreenshotUrls
     */
    public void setIpadScreenshotUrls(List<Object> ipadScreenshotUrls) {
        this.ipadScreenshotUrls = ipadScreenshotUrls;
    }

    /**
     * @return The artworkUrl512
     */
    public String getArtworkUrl512() {
        return artworkUrl512;
    }

    /**
     * @param artworkUrl512 The artworkUrl512
     */
    public void setArtworkUrl512(String artworkUrl512) {
        this.artworkUrl512 = artworkUrl512;
    }

    /**
     * @return The price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return The sellerName
     */
    @Override
    public String getSellerName() {
        return sellerName;
    }

    /**
     * @param sellerName The sellerName
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * @return The bundleId
     */
    public String getBundleId() {
        return bundleId;
    }

    /**
     * @param bundleId The bundleId
     */
    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    /**
     * @return The primaryGenreId
     */
    public Integer getPrimaryGenreId() {
        return primaryGenreId;
    }

    /**
     * @param primaryGenreId The primaryGenreId
     */
    public void setPrimaryGenreId(Integer primaryGenreId) {
        this.primaryGenreId = primaryGenreId;
    }

    /**
     * @return The releaseNotes
     */
    public String getReleaseNotes() {
        return releaseNotes;
    }

    /**
     * @param releaseNotes The releaseNotes
     */
    public void setReleaseNotes(String releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    /**
     * @return The minimumOsVersion
     */
    public String getMinimumOsVersion() {
        return minimumOsVersion;
    }

    /**
     * @param minimumOsVersion The minimumOsVersion
     */
    public void setMinimumOsVersion(String minimumOsVersion) {
        this.minimumOsVersion = minimumOsVersion;
    }

    /**
     * @return The formattedPrice
     */
    public String getFormattedPrice() {
        return formattedPrice;
    }

    /**
     * @param formattedPrice The formattedPrice
     */
    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    /**
     * @return The languageCodesISO2A
     */
    public List<String> getLanguageCodesISO2A() {
        return languageCodesISO2A;
    }

    /**
     * @param languageCodesISO2A The languageCodesISO2A
     */
    public void setLanguageCodesISO2A(List<String> languageCodesISO2A) {
        this.languageCodesISO2A = languageCodesISO2A;
    }

    /**
     * @return The fileSizeBytes
     */
    public Long getFileSizeBytes() {
        return fileSizeBytes;
    }

    /**
     * @param fileSizeBytes The fileSizeBytes
     */
    public void setFileSizeBytes(Long fileSizeBytes) {
        this.fileSizeBytes = fileSizeBytes;
    }

    /**
     * @return The averageUserRatingForCurrentVersion
     */
    public Double getAverageUserRatingForCurrentVersion() {
        return averageUserRatingForCurrentVersion;
    }

    /**
     * @param averageUserRatingForCurrentVersion The averageUserRatingForCurrentVersion
     */
    public void setAverageUserRatingForCurrentVersion(Double averageUserRatingForCurrentVersion) {
        this.averageUserRatingForCurrentVersion = averageUserRatingForCurrentVersion;
    }

    /**
     * @return The userRatingCountForCurrentVersion
     */
    public Integer getUserRatingCountForCurrentVersion() {
        return userRatingCountForCurrentVersion;
    }

    /**
     * @param userRatingCountForCurrentVersion The userRatingCountForCurrentVersion
     */
    public void setUserRatingCountForCurrentVersion(Integer userRatingCountForCurrentVersion) {
        this.userRatingCountForCurrentVersion = userRatingCountForCurrentVersion;
    }

    /**
     * @return The trackContentRating
     */
    public String getTrackContentRating() {
        return trackContentRating;
    }

    /**
     * @param trackContentRating The trackContentRating
     */
    public void setTrackContentRating(String trackContentRating) {
        this.trackContentRating = trackContentRating;
    }

    /**
     * @return The averageUserRating
     */
    public Double getAverageUserRating() {
        return averageUserRating;
    }

    /**
     * @param averageUserRating The averageUserRating
     */
    public void setAverageUserRating(Double averageUserRating) {
        this.averageUserRating = averageUserRating;
    }

    /**
     * @return The userRatingCount
     */
    public Integer getUserRatingCount() {
        return userRatingCount;
    }

    /**
     * @param userRatingCount The userRatingCount
     */
    public void setUserRatingCount(Integer userRatingCount) {
        this.userRatingCount = userRatingCount;
    }

    /**
     * @return The artistIds
     */
    public List<Integer> getArtistIds() {
        return artistIds;
    }

    /**
     * @param artistIds The artistIds
     */
    public void setArtistIds(List<Integer> artistIds) {
        this.artistIds = artistIds;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItunesItem)) {
            return false;
        }
        ItunesItem result2 = (ItunesItem) obj;
        return getItemId()
                .equals(result2.getItemId());
    }

    @Override
    public String getArtworkUrl() {
        return artworkUrl100;
    }

    @Override
    public String getItemPrice() {
        if (formattedPrice != null) return formattedPrice;
        Double price;
        if (wrapperType == null) price = trackPrice;
        else price = wrapperType.equals(COLLECTION) ? collectionPrice : trackPrice;
        if (price == null) return null;
        else if (price < 0) return wrapperType.equals("track") ? "Album Only" : "Partial Album";
        else return price == 0.0 ? "Free" : "$" + Double.toString(price);
    }

    @Override
    public String getItemRentalPrice() {
        if (trackRentalPrice != null)
            return trackRentalPrice == 0.0 ? "Free" : "$" + Double.toString(trackRentalPrice);
        else return null;
    }

    @Override
    public String getArtworkUrlHD() {
        return artworkUrl512;
    }

    @Override
    public String getPublisher() {
        return null;
    }

    @Override
    public String getItemSummary() {
        if (longDescription != null)
            return StringEscapeUtils.unescapeXml(longDescription.replaceAll("\\<[^>]*>", ""));
        else if (description != null)
            return StringEscapeUtils.unescapeXml(description.replaceAll("\\<[^>]*>", ""));
        else return null;
    }

    @Override
    public String getItemId() {
        if (trackId != null)
            return Integer.toString(trackId);
        else
            return Integer.toString(collectionId);
    }

    @Override
    public String getContentType() {
        if (wrapperType == null) return kind;
        return wrapperType.equals(COLLECTION) ? collectionType : kind;
    }

    @Override
    public SimpleDateFormat getDateFormat() {
        return simpleDateFormat;
    }

    @Override
    public String getItemGenre() {
        if (primaryGenreName != null || !genres.isEmpty())
            return primaryGenreName != null ? primaryGenreName : genres.get(0);
        else return null;
    }

    @Override
    public String getItemName() {
        if (wrapperType == null) return trackName;
        else return wrapperType.equals(COLLECTION) ? collectionName : trackName;
    }

    @Override
    public String getItemUrl() {
        if (wrapperType == null) return trackViewUrl;
        return wrapperType.equals(COLLECTION) ? collectionViewUrl : trackViewUrl;
    }

    @Override
    public String getItemCollectionName() {
        if (wrapperType == null) return collectionName;
        return wrapperType.equals(COLLECTION) ? null : collectionName;
    }
}
