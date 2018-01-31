package com.example.android.quakereport;

/**
 * Created by praty on 21-Dec-17.
 */

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl;
    public  Earthquake(Double Magnitude, String Location, long Date, String url)
    {
        mMagnitude=Magnitude;
        mLocation=Location;
        mDate=Date;
        mUrl=url;
    }

    public String getmUrl() {
        return mUrl;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Double getmMagnitude() {
        return mMagnitude;
    }
}
