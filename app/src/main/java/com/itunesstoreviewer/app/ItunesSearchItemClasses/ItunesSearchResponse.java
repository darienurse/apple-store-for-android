package com.itunesstoreviewer.app.ItunesSearchItemClasses;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated("org.jsonschema2pojo")
public class ItunesSearchResponse {

    @Expose
    private Integer resultCount;
    @Expose
    private List<Result> results = new ArrayList<Result>();

    /**
     * @return The resultCount
     */
    public Integer getResultCount() {
        return resultCount;
    }

    /**
     * @param resultCount The resultCount
     */
    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    /**
     * @return The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

}
