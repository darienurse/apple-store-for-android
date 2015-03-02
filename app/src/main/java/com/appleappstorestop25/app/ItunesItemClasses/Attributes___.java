
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Attributes___ implements Serializable {

    @Expose
    private String rel;
    @Expose
    private String type;
    @Expose
    private String href;

    /**
     * 
     * @return
     *     The rel
     */
    public String getRel() {
        return rel;
    }

    /**
     * 
     * @param rel
     *     The rel
     */
    public void setRel(String rel) {
        this.rel = rel;
    }

    public Attributes___ withRel(String rel) {
        this.rel = rel;
        return this;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Attributes___ withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 
     * @return
     *     The href
     */
    public String getHref() {
        return href;
    }

    /**
     * 
     * @param href
     *     The href
     */
    public void setHref(String href) {
        this.href = href;
    }

    public Attributes___ withHref(String href) {
        this.href = href;
        return this;
    }

}
