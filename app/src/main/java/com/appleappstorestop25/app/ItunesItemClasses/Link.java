
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("org.jsonschema2pojo")
public class Link {

    @Expose
    @Valid
    private Attributes___ attributes;

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes___ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes___ attributes) {
        this.attributes = attributes;
    }

    public Link withAttributes(Attributes___ attributes) {
        this.attributes = attributes;
        return this;
    }

}
