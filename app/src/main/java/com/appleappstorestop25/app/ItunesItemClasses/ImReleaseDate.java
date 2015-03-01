
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("org.jsonschema2pojo")
public class ImReleaseDate {

    @Expose
    private String label;
    @Expose
    @Valid
    private Attributes_______ attributes;

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

    public ImReleaseDate withLabel(String label) {
        this.label = label;
        return this;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes_______ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes_______ attributes) {
        this.attributes = attributes;
    }

    public ImReleaseDate withAttributes(Attributes_______ attributes) {
        this.attributes = attributes;
        return this;
    }

}
