package com.nevictus.android.coolmix;

public class Data {

    //Data Title
    private String mDataTitle;
    //Data Address
    private String mDataAddress;
    //Data' website
    private String mDataSite;
    //Data Description
    private  String mDataDescription;
    //Museum Image Cover
    private  int mDataImgId;
    //Constant for museum with no image
    private static final int NO_IMAGE_PROVIDED= -1;

    //Constructor
    public Data(int dataImgId, String dataTitle,
                String dataAddress, String dataSite, String dataDescription) {

        mDataImgId = dataImgId;
        mDataTitle = dataTitle;
        mDataAddress = dataAddress;
        mDataSite = dataSite;
        mDataDescription = dataDescription;
    }

    //Get the museum Associated Image
    public  int getDataImgId() {
        return mDataImgId;
    }
    //Return regardless there is an image or not
    public  boolean hasImage() {
        return  mDataImgId != NO_IMAGE_PROVIDED;
    }
    //Get Museum title
    public String getDataTitle() {
        return mDataTitle;
    }
    //Get Museum address
    public String getDataAddress() {
        return mDataAddress;
    }
    //Get Museum Website
    public String getDataSite() {
        return mDataSite;
    }
    //Get the museum's Description
    public String getDataDescription() {
        return mDataDescription;
    }
}
