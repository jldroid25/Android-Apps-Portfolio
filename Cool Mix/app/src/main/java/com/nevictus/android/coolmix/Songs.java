package com.nevictus.android.coolmix;

public class Songs {

    //Song title
    private String mTitle;
    //Song Description
    private  String mSongDescription;
    //Artist Name
    private  String mArtist;
    //Date Album was release
    private String mReleaseDate;
    //Song Art Cover
    private  int mSongImgId;
    //For the audiofile
    private  int mAudioFile;
    //Constant for song with no image
    private static final int NO_IMAGE_PROVIDED= -1;

    //Constructor
    public  Songs(int songImgId, String title, String description,
                  String artist, String releaseDate, int audioFile) {
        mSongImgId = songImgId;
        mTitle = title;
        mSongDescription = description;
        mArtist = artist;
        mReleaseDate = releaseDate;
        mAudioFile = audioFile;
    }

    //Get the Art cover
    public  int getSongImgId() {
        return mSongImgId;
    }

    //Return regardless there is an image or not
    public  boolean hasImage() {
        return  mSongImgId != NO_IMAGE_PROVIDED;
    }

    //Get song title
    public String getTitle() {
        return "Title: " + mTitle;
    }

    //Get the song Description
    public String getDescription() {
        return  mSongDescription;
    }

    //Get the Artist
    public String getArtist() {
        return "Artist: " + mArtist;
    }

    //Get the Song Release Date
    public String getReleaseDate() {
        return "Date: " + mReleaseDate;
    }

    //Get the Audio file
    public int getAudioFile() {
        return  mAudioFile;
    }
}//end class