
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Id implements Serializable {

    @Expose
    private String label;
    @Expose
    @Valid
    private Attributes____ attributes;

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

    public Id withLabel(String label) {
        this.label = label;
        return this;
    }

    /**
     * 
     * @return
     *     The attributes
     */
    public Attributes____ getAttributes() {
        return attributes;
    }

    /**
     * 
     * @param attributes
     *     The attributes
     */
    public void setAttributes(Attributes____ attributes) {
        this.attributes = attributes;
    }

    public Id withAttributes(Attributes____ attributes) {
        this.attributes = attributes;
        return this;
    }

}
