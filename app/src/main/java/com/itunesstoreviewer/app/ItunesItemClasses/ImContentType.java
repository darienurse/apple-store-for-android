package com.itunesstoreviewer.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ImContentType implements Serializable {

    @SerializedName("im:contentType")
    @Expose
    private ImContentType_ imContentType;
    @Expose
    private Attributes___ attributes;

    /**
     * @return The imContentType
     */
    public ImContentType_ getImContentType() {
        return imContentType;
    }

    /**
     * @param imContentType The im:contentType
     */
    public void setImContentType(ImContentType_ imContentType) {
        this.imContentType = imContentType;
    }

    /**
     * @return The attributes
     */
    public Attributes___ getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(Attributes___ attributes) {
        this.attributes = attributes;
    }

}
