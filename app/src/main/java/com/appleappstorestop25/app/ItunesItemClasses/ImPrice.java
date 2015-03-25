package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class ImPrice implements Serializable {

    @Expose
    private String label;
    @Expose
    private Attributes_ attributes;

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
    public Attributes_ getAttributes() {
        return attributes;
    }

    /**
     * @param attributes The attributes
     */
    public void setAttributes(Attributes_ attributes) {
        this.attributes = attributes;
    }

}
