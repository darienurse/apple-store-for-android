package com.itunesstoreviewer.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Category implements Serializable {

    @Expose
    private Attributes_______ attributes;

    /**
     * @return The attributes
     */
    public Attributes_______ getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(Attributes_______ attributes) {
        this.attributes = attributes;
    }

}
