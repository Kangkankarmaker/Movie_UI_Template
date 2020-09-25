package com.example.movieuitemplate.models;

public class Movie {

    private String title,dec,stdio,rating,streamingLink;
    private int thumblnall, coverImg;

    public Movie(String title, String dec, String stdio, String rating, String streamingLink, int thumblnall) {
        this.title = title;
        this.dec = dec;
        this.stdio = stdio;
        this.rating = rating;
        this.streamingLink = streamingLink;
        this.thumblnall = thumblnall;
    }

    public Movie(String title, int thumblnall, int coverImg) {
        this.title = title;
        this.thumblnall = thumblnall;
        this.coverImg = coverImg;
    }

    public Movie(String title, int thumblnall) {
        this.title = title;
        this.thumblnall = thumblnall;
    }

    public int getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(int coverImg) {
        this.coverImg = coverImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getStdio() {
        return stdio;
    }

    public void setStdio(String stdio) {
        this.stdio = stdio;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStreamingLink() {
        return streamingLink;
    }

    public void setStreamingLink(String streamingLink) {
        this.streamingLink = streamingLink;
    }

    public int getThumblnall() {
        return thumblnall;
    }

    public void setThumblnall(int thumblnall) {
        this.thumblnall = thumblnall;
    }
}
