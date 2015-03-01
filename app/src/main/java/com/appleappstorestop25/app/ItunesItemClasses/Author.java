
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;

@Generated("org.jsonschema2pojo")
public class Author {

    @Expose
    @Valid
    private Name name;
    @Expose
    @Valid
    private Uri uri;

    /**
     * 
     * @return
     *     The name
     */
    public Name getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(Name name) {
        this.name = name;
    }

    public Author withName(Name name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The uri
     */
    public Uri getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *     The uri
     */
    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Author withUri(Uri uri) {
        this.uri = uri;
        return this;
    }

}
