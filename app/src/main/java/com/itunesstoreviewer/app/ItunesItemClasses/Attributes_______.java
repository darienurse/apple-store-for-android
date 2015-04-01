package com.itunesstoreviewer.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Attributes_______ implements Serializable {

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
     * @return The imId
     */
    public String getImId() {
        return imId;
    }

    /**
     * @param imId The im:id
     */
    public void setImId(String imId) {
        this.imId = imId;
    }

    /**
     * @return The term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term The term
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return The scheme
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * @param scheme The scheme
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

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

}