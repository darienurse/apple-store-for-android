package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Attributes____ implements Serializable {

    @SerializedName("im:id")
    @Expose
    private String imId;
    @SerializedName("im:bundleId")
    @Expose
    private String imBundleId;

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

    public Attributes____ withImId(String imId) {
        this.imId = imId;
        return this;
    }

    /**
     * @return The imBundleId
     */
    public String getImBundleId() {
        return imBundleId;
    }

    /**
     * @param imBundleId The im:bundleId
     */
    public void setImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
    }

    public Attributes____ withImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
        return this;
    }

}
