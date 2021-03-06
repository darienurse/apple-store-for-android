package com.itunesstoreviewer.app.ItunesRssItemClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.itunesstoreviewer.app.ItunesItem;
import org.apache.commons.lang.StringEscapeUtils;

import javax.annotation.Generated;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Entry implements ItunesItem {

    final private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private String contentTypePrefix = "";

    @SerializedName("im:name")
    @Expose
    private ImName imName;
    @SerializedName("im:image")
    @Expose
    private List<ImImage> imImage = new ArrayList<ImImage>();
    @Expose
    private Summary summary;
    @SerializedName("im:price")
    @Expose
    private ImPrice imPrice;
    @SerializedName("im:contentType")
    @Expose
    private ImContentType imContentType;
    @Expose
    private Rights rights;
    @Expose
    private Title title;
    @Expose
    private Link[] link;
    @Expose
    private Id id;
    @SerializedName("im:artist")
    @Expose
    private ImArtist imArtist;
    @Expose
    private Category category;
    @SerializedName("im:releaseDate")
    @Expose
    private ImReleaseDate imReleaseDate;
    @Expose
    private Subtitle subtitle;
    @SerializedName("im:vendorName")
    @Expose
    private ImVendorName imVendorName;
    @SerializedName("im:publisher")
    @Expose
    private ImPublisher imPublisher;
    @SerializedName("im:rentalPrice")
    @Expose
    private ImRentalPrice imRentalPrice;
    @SerializedName("im:collection")
    @Expose
    private ImCollection imCollection;
    @SerializedName("im:itemCount")
    @Expose
    private ImItemCount imItemCount;

    /**
     * @return The imName
     */
    public ImName getImName() {
        return imName;
    }

    /**
     * @param imName The im:name
     */
    public void setImName(ImName imName) {
        this.imName = imName;
    }

    /**
     * @return The imImage
     */
    public List<ImImage> getImImage() {
        return imImage;
    }

    /**
     * @param imImage The im:image
     */
    public void setImImage(List<ImImage> imImage) {
        this.imImage = imImage;
    }

    /**
     * @return The summary
     */
    public Summary getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    /**
     * @return The imPrice
     */
    public ImPrice getImPrice() {
        return imPrice;
    }

    /**
     * @param imPrice The im:price
     */
    public void setImPrice(ImPrice imPrice) {
        this.imPrice = imPrice;
    }

    /**
     * @return The imContentType
     */
    public ImContentType getImContentType() {
        return imContentType;
    }

    /**
     * @param imContentType The im:contentType
     */
    public void setImContentType(ImContentType imContentType) {
        this.imContentType = imContentType;
    }

    /**
     * @return The rights
     */
    public Rights getRights() {
        return rights;
    }

    /**
     * @param rights The rights
     */
    public void setRights(Rights rights) {
        this.rights = rights;
    }

    /**
     * @return The title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * @return The link
     */
    public List<Link> getLink() {
        return Arrays.asList(link);
    }

    /**
     * @param link The link
     */
    public void setLink(Link[] link) {
        this.link = link;
    }

    /**
     * @return The id
     */
    public Id getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Id id) {
        this.id = id;
    }

    /**
     * @return The imArtist
     */
    public ImArtist getImArtist() {
        return imArtist;
    }

    /**
     * @param imArtist The im:artist
     */
    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    /**
     * @return The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return The imReleaseDate
     */
    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }

    /**
     * @param imReleaseDate The im:releaseDate
     */
    public void setImReleaseDate(ImReleaseDate imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
    }

    /**
     * @return The subtitle
     */
    public Subtitle getSubtitle() {
        return subtitle;
    }

    /**
     * @param subtitle The subtitle
     */
    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * @return The imVendorName
     */
    public ImVendorName getImVendorName() {
        return imVendorName;
    }

    /**
     * @param imVendorName The im:vendorName
     */
    public void setImVendorName(ImVendorName imVendorName) {
        this.imVendorName = imVendorName;
    }

    /**
     * @return The imPublisher
     */
    public ImPublisher getImPublisher() {
        return imPublisher;
    }

    /**
     * @param imPublisher The im:publisher
     */
    public void setImPublisher(ImPublisher imPublisher) {
        this.imPublisher = imPublisher;
    }

    /**
     * @return The imRentalPrice
     */
    public ImRentalPrice getImRentalPrice() {
        return imRentalPrice;
    }

    /**
     * @param imRentalPrice The im:rentalPrice
     */
    public void setImRentalPrice(ImRentalPrice imRentalPrice) {
        this.imRentalPrice = imRentalPrice;
    }

    /**
     * @return The imCollection
     */
    public ImCollection getImCollection() {
        return imCollection;
    }

    /**
     * @param imCollection The im:collection
     */
    public void setImCollection(ImCollection imCollection) {
        this.imCollection = imCollection;
    }

    /**
     * @return The imItemCount
     */
    public ImItemCount getImItemCount() {
        return imItemCount;
    }

    /**
     * @param imItemCount The im:itemCount
     */
    public void setImItemCount(ImItemCount imItemCount) {
        this.imItemCount = imItemCount;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItunesItem)) {
            return false;
        }
        ItunesItem entry2 = (ItunesItem) obj;
        return this.getItemId()
                .equals(entry2.getItemId());
    }

    @Override
    public String getItemId() {
        if (id != null)
            return id.getAttributes().getImId();
        else
            return null;
    }

    @Override
    public String getArtistName() {
        if (imArtist != null) {
            return imArtist.getLabel();
        } else {
            return null;
        }
    }

    @Override
    public String getItemName() {
        if (imName != null) {
            return imName.getLabel();
        } else {
            return null;
        }
    }

    @Override
    public String getItemUrl() {
        if (link != null)
            return Arrays.asList(link).get(0).getAttributes().getHref();
        else return null;
    }

    @Override
    public String getArtworkUrl() {
        if (!imImage.isEmpty()) {
            return imImage.get(2).getLabel();
        } else {
            return null;
        }
    }

    @Override
    public String getItemPrice() {
        if (imPrice != null) {
            String price = imPrice.getLabel().replaceFirst("-", "");
            return price.equals("Get") ? "Free" : price;
        } else return null;
    }

    @Override
    public String getItemRentalPrice() {
        if (imRentalPrice != null)
            return imRentalPrice.getLabel();
        else
            return null;
    }

    @Override
    public String getReleaseDate() {
        if (imReleaseDate != null)
            return imReleaseDate.getLabel();
        else return null;
    }

    @Override
    public String getItemGenre() {
        if (category != null)
            return category.getAttributes().getTerm();
        else return null;
    }

    @Override
    public String getItemSummary() {
        if (summary != null)
            return StringEscapeUtils.unescapeXml(summary.getLabel().replaceAll("\\<[^>]*>", ""));
        else return null;
    }

    @Override
    public String getCopyright() {
        if (rights != null)
            return rights.getLabel();
        else return null;
    }

    @Override
    public String getSellerName() {
        if (imVendorName != null)
            return imVendorName.getLabel();
        else return null;
    }

    @Override
    public List<String> getScreenshotUrls() {
        return null;
    }

    @Override
    public String getArtworkUrlHD() {
        return null;
    }

    @Override
    public String getItemCollectionName() {
        if (imCollection != null)
            return imCollection.getImName().getLabel();
        else return null;
    }

    @Override
    public String getContentType() {
        if (imContentType != null) {
            if (imContentType.getImContentType() != null)
                return contentTypePrefix+imContentType.getImContentType().getAttributes().getLabel();
            else
                return contentTypePrefix+imContentType.getAttributes().getLabel();
        } else return null;
    }

    @Override
    public String getPublisher() {
        if (imPublisher != null)
            return imPublisher.getLabel();
        else
            return null;
    }

    @Override
    public SimpleDateFormat getDateFormat() {
        return simpleDateFormat;
    }

    public void setContentTypePrefix(String contentTypePrefix) {
        this.contentTypePrefix = contentTypePrefix;
    }
}