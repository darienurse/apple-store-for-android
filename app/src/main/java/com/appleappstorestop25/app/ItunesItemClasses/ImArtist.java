
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("org.jsonschema2pojo")
public class ImArtist {

    @Expose
    private String label;
    @Expose
    @Valid
    private Attributes_____ attributes;

    /**
     * 
     * @return
     *     The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    public ImArtist withLabel(String label) {
        this.label = label;
        return this;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes_____ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes_____ attributes) {
        this.attributes = attributes;
    }

    public ImArtist withAttributes(Attributes_____ attributes) {
        this.attributes = attributes;
        return this;
    }

}
