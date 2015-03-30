package com.itunesstoreviewer.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ImCollection implements Serializable {

    @SerializedName("im:name")
    @Expose
    private ImName_ imName;
    @Expose
    private Link_ link;
    @SerializedName("im:contentType")
    @Expose
    private ImContentType__ imContentType;

    /**
     * @return The imName
     */
    public ImName_ getImName() {
        return imName;
    }

    /**
     * @param imName The im:name
     */
    public void setImName(ImName_ imName) {
        this.imName = imName;
    }

    /**
     * @return The link
     */
    public Link_ getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(Link_ link) {
        this.link = link;
    }

    /**
     * @return The imContentType
     */
    public ImContentType__ getImContentType() {
        return imContentType;
    }

    /**
     * @param imContentType The im:contentType
     */
    public void setImContentType(ImContentType__ imContentType) {
        this.imContentType = imContentType;
    }

}
