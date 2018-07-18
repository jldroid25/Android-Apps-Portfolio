package com.nevictus.android.coolmix;

public class Cities {

    //City Name
    private String mCityName;
    //city Description
    private  String mCityDescription;
    //City Art Cover
    private  int mCityImgId;
    //Constant for song with no image
    private static final int NO_IMAGE_PROVIDED= -1;

    //Constructor
    public Cities(int cityImgId, String cityName, String cityDescription) {
        mCityImgId = cityImgId;
        mCityName = cityName;
        mCityDescription = cityDescription;
    }

    //Get the City Associated Image
    public  int getCityImgId() {
        return mCityImgId;
    }
    //Return regardless there is an image or not
    public  boolean hasImage() {
        return  mCityImgId != NO_IMAGE_PROVIDED;
    }
    //Get City Name
    public String getCityName() {
        return  mCityName;
    }
    //Get the City's Description
    public String getDescription() {
        return  mCityDescription;
    }

}//end class