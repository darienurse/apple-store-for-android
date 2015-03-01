
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Attributes______ {

    @SerializedName("im:id")
    @Expose
    private String imId;
    @Expose
    private String term;
    @Expose
    private String scheme;
    @Expose
    private String label;

    /**
     * 
     * @return
     *     The imId
     */
    public String getImId() {
        return imId;
    }

    /**
     * 
     * @param imId
     *     The im:id
     */
    public void setImId(String imId) {
        this.imId = imId;
    }

    public Attributes______ withImId(String imId) {
        this.imId = imId;
        return this;
    }

    /**
     * 
     * @return
     *     The term
     */
    public String getTerm() {
        return term;
    }

    /**
     * 
     * @param term
     *     The term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    public Attributes______ withTerm(String term) {
        this.term = term;
        return this;
    }

    /**
     * 
     * @return
     *     The scheme
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * 
     * @param scheme
     *     The scheme
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Attributes______ withScheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

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

    public Attributes______ withLabel(String label) {
        this.label = label;
        return this;
    }

}
