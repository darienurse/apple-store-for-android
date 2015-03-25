package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ImContentType__ implements Serializable {

    @SerializedName("im:contentType")
    @Expose
    private ImContentType___ imContentType;
    @Expose
    private Attributes____________ attributes;

    /**
     * @return The imContentType
     */
    public ImContentType___ getImContentType() {
        return imContentType;
    }

    /**
     * @param imContentType The im:contentType
     */
    public void setImContentType(ImContentType___ imContentType) {
        this.imContentType = imContentType;
    }

    /**
     * @return The attributes
     */
    public Attributes____________ getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(Attributes____________ attributes) {
        this.attributes = attributes;
    }

}
