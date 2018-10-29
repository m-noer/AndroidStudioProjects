package com.example.mnoer.cardview;

public class ExampleItem {
    private String mImageUrl;
    private String mTitle;
    private String mOverview;

    public ExampleItem(String imageUrl, String creator, String overview) {
        mImageUrl = imageUrl;
        mTitle = creator;
        mOverview = overview;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmOverview() {
        return mOverview;
    }
}
