
package com.appleappstorestop25.app.ItunesItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Feed {

    @Expose
    @Valid
    private Author author;
    @Expose
    @Valid
    private List<Entry> entry = new ArrayList<Entry>();
    @Expose
    @Valid
    private Updated updated;
    @Expose
    @Valid
    private Rights_ rights;
    @Expose
    @Valid
    private Title_ title;
    @Expose
    @Valid
    private Icon icon;
    @Expose
    @Valid
    private List<Link_> link = new ArrayList<Link_>();
    @Expose
    @Valid
    private Id_ id;

    /**
     * 
     * @return
     *     The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    public Feed withAuthor(Author author) {
        this.author = author;
        return this;
    }

    /**
     * 
     * @return
     *     The entry
     */
    public List<Entry> getEntry() {
        return entry;
    }

    /**
     * 
     * @param entry
     *     The entry
     */
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public Feed withEntry(List<Entry> entry) {
        this.entry = entry;
        return this;
    }

    /**
     * 
     * @return
     *     The updated
     */
    public Updated getUpdated() {
        return updated;
    }

    /**
     * 
     * @param updated
     *     The updated
     */
    public void setUpdated(Updated updated) {
        this.updated = updated;
    }

    public Feed withUpdated(Updated updated) {
        this.updated = updated;
        return this;
    }

    /**
     * 
     * @return
     *     The rights
     */
    public Rights_ getRights() {
        return rights;
    }

    /**
     * 
     * @param rights
     *     The rights
     */
    public void setRights(Rights_ rights) {
        this.rights = rights;
    }

    public Feed withRights(Rights_ rights) {
        this.rights = rights;
        return this;
    }

    /**
     * 
     * @return
     *     The title
     */
    public Title_ getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(Title_ title) {
        this.title = title;
    }

    public Feed withTitle(Title_ title) {
        this.title = title;
        return this;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Feed withIcon(Icon icon) {
        this.icon = icon;
        return this;
    }

    /**
     * 
     * @return
     *     The link
     */
    public List<Link_> getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(List<Link_> link) {
        this.link = link;
    }

    public Feed withLink(List<Link_> link) {
        this.link = link;
        return this;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Id_ getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Id_ id) {
        this.id = id;
    }

    public Feed withId(Id_ id) {
        this.id = id;
        return this;
    }

}
