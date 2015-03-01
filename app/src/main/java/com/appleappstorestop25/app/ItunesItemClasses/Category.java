
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("org.jsonschema2pojo")
public class Category {

    @Expose
    @Valid
    private Attributes______ attributes;

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes______ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes______ attributes) {
        this.attributes = attributes;
    }

    public Category withAttributes(Attributes______ attributes) {
        this.attributes = attributes;
        return this;
    }

}
