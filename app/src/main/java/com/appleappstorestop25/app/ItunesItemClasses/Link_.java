
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("org.jsonschema2pojo")
public class Link_ {

    @Expose
    @Valid
    private Attributes________ attributes;

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes________ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes________ attributes) {
        this.attributes = attributes;
    }

    public Link_ withAttributes(Attributes________ attributes) {
        this.attributes = attributes;
        return this;
    }

}
