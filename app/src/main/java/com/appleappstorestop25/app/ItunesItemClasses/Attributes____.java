package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Attributes____ implements Serializable {

    @Expose
    private String rel;
    @Expose
    private String type;
    @Expose
    private String href;

    /**
     * @return The rel
     */
    public String getRel() {
        return rel;
    }

    /**
     * @param rel The rel
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The href
     */
    public String getHref() {
        return href;
    }

    /**
     * @param href The href
     */
    public void setHref(String href) {
        this.href = href;
    }

}
