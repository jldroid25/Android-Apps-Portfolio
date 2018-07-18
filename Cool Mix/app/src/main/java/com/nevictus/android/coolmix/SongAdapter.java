package com.nevictus.android.coolmix;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Songs> {

    //Custom Constructor
    public SongAdapter(Activity context, ArrayList<Songs> Songs) {
        super(context, 0, Songs);
    }

    //To provide the view & inflate it if not already exists with new songs
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View songItemView = convertView;

        if (songItemView == null) {
            songItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,
                    false);
        }
        //find the item position
        Songs singleSong = getItem(position);

        //Retrieve & set the song's Art cover
        ImageView songImg = songItemView.findViewById(R.id.artCover);
        songImg.setImageResource(singleSong.getSongImgId());

        //Check if an image is available
        if (singleSong.hasImage()) {
            songImg.setImageResource(singleSong.getSongImgId());
            //Make it visible
            songImg.setVisibility(View.VISIBLE);
        } else {
            //hide the imageView
            songImg.setVisibility(View.GONE);
        }

        //Retrieve & set the song's title
        TextView tiTleTxtView = songItemView.findViewById(R.id.title);
        tiTleTxtView.setText(singleSong.getTitle());

        //Retrieve & set the song's Description
        TextView descTxtView = songItemView.findViewById(R.id.description);
        descTxtView.setText(singleSong.getDescription());

        //Retrieve & set the song's Artist
        TextView artisTxtView = songItemView.findViewById(R.id.artist);
        artisTxtView.setText(singleSong.getArtist());

        //Retrieve & set the song's Release Date
        TextView releaseDateTxtView = songItemView.findViewById(R.id.releaseDate);
        releaseDateTxtView.setText(singleSong.getReleaseDate());

        return songItemView;
    }//end getView()
}//end class