package com.itunesstoreviewer.app.ItunesRssItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ImArtist implements Serializable {

    @Expose
    private String label;
    @Expose
    private Attributes______ attributes;

    /**
     * @return The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return The attributes
     */
    public Attributes______ getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(Attributes______ attributes) {
        this.attributes = attributes;
    }

}
