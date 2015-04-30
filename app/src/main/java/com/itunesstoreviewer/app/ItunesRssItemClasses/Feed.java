package com.itunesstoreviewer.app.ItunesRssItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class Feed implements Serializable {

    @Expose
    private Author author;
    @Expose
    private List<Entry> entry = new ArrayList<Entry>();
    @Expose
    private Updated updated;
    @Expose
    private Rights_ rights;
    @Expose
    private Title_ title;
    @Expose
    private Icon icon;
    @Expose
    private List<Link__> link = new ArrayList<Link__>();
    @Expose
    private Id_ id;

    /**
     * @return The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return The entry
     */
    public List<Entry> getEntry() {
        return entry;
    }

    /**
     * @param entry The entry
     */
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    /**
     * @return The updated
     */
    public Updated getUpdated() {
        return updated;
    }

    /**
     * @param updated The updated
     */
    public void setUpdated(Updated updated) {
        this.updated = updated;
    }

    /**
     * @return The rights
     */
    public Rights_ getRights() {
        return rights;
    }

    /**
     * @param rights The rights
     */
    public void setRights(Rights_ rights) {
        this.rights = rights;
    }

    /**
     * @return The title
     */
    public Title_ getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(Title_ title) {
        this.title = title;
    }

    /**
     * @return The icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * @return The link
     */
    public List<Link__> getLink() {
        return link;
    }

    /**
     * @param link The link
     */
    public void setLink(List<Link__> link) {
        this.link = link;
    }

    /**
     * @return The id
     */
    public Id_ getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Id_ id) {
        this.id = id;
    }

}
