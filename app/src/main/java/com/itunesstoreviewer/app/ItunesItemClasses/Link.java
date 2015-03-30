package com.itunesstoreviewer.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Link implements Serializable {

    @Expose
    private Attributes____ attributes;

    /**
     * @return The attributes
     */
    public Attributes____ getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(Attributes____ attributes) {
        this.attributes = attributes;
    }

}
