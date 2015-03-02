package com.appleappstorestop25.app;

import android.graphics.Color;

/**
 * Created by Darien on 3/2/2015.
 */
public class CategoryAttribute {
    private final String title;
    private final Integer color;
    private final String url;

    public CategoryAttribute(String title, Integer c, String url){
        this.title = title;
        this.color = c;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public Integer getColor() {
        return color;
    }

    public String getTitle() {
        return title;
    }
}
