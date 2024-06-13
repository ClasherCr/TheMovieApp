package com.example.themovieapp.model;

import android.graphics.Movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {


    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPage;
    @SerializedName("total_results")
    @Expose
    private Integer totalResult;
    @SerializedName("results")
    @Expose
    private List<Movie> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public Result() {
    }
}
