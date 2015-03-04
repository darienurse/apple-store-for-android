package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ImContentType implements Serializable {

    @Expose
    @Valid
    private Attributes__ attributes;

    /**
     * @return The attributes
     */
    public Attributes__ getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(Attributes__ attributes) {
        this.attributes = attributes;
    }

    public ImContentType withAttributes(Attributes__ attributes) {
        this.attributes = attributes;
        return this;
    }

}
