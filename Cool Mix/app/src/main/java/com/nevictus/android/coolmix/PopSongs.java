package com.nevictus.android.coolmix;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;

public class PopSongs extends AppCompatActivity {

    //Handle play back of all audio files
    private MediaPlayer mSongPlayer;

    //Handle audio focus when playing the sound File
    private AudioManager mAudioManager;

    //To be called when the mediaPlayer had done playing the audiofile
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            //Since the sound file is done playing, release the media palyer resources
            releaseMediaPlayer();
        }
    };

    /*
     * This listener gets triggered whenever the audio focus changes
     * We gain or lose audio focus because of another app or device
     *
     * */
    private AudioManager.OnAudioFocusChangeListener mOnAudiofocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //Pause to reset the file
                        mSongPlayer.pause();
                        //Resume playing from the beginning
                        mSongPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //We have regain focus & can resume playback
                        mSongPlayer.start();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //We've loss audio focus, stop playback , & clean the resources
                        releaseMediaPlayer();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);

        //Create and setup the AudioManager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Arraylist of Electronic Songs
        final ArrayList<Songs> popSongs = new ArrayList<Songs>();

        popSongs.add(new Songs(R.drawable.poppic1, "Creative Mind",
                "Inspiring Pop song music featuring guitars & more.",
                "Jackson M", "2018", R.raw.popsong1));
        popSongs.add(new Songs(R.drawable.poppic2, "Little Idea", "la la laluba pop song.",
                "Tomato Cake", "2017", R.raw.popsong2));
        popSongs.add(new Songs(R.drawable.poppic3, "Hey, Hey",
                "Chearful & fun track featuring piane & more.",
                "Timbersea J", "2016", R.raw.popsong3));
        popSongs.add(new Songs(R.drawable.poppic4, "Energy",
                "Motivational music track to get you going.",
                "Mozart B", "2018", R.raw.popsong4));
        popSongs.add(new Songs(R.drawable.poppic5, "Clear Day",
                " Coconut water got nothing on this track.",
                "Pure H2O", "2017", R.raw.popsong5));

        //Using ArrayAdapter & ListView  to Recycle the Views
        SongAdapter popSongAdapter = new SongAdapter(this, popSongs);
        ListView popSongListView = findViewById(R.id.list);
        popSongListView.setAdapter(popSongAdapter);

        //Click to play the Song
        popSongListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Release the MediaPlayer if exists, we're about to play a different song
                releaseMediaPlayer();

                //Get the object at a given position
                Songs audio = popSongs.get(position);

                /*
                 *Re quest audio focus to play the audio file.
                 * We using AUDIOFOCUS_GAIN_TRANSIENT since our audio files are short in length.
                 *
                 * */
                int result = mAudioManager.requestAudioFocus(mOnAudiofocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    /*
                    We have the audio Focus now
                    Create the setup the Mediaplayer for the audio resource associated
                    with the current song
                    */

                    mSongPlayer = MediaPlayer.create(PopSongs.this, audio.getAudioFile());
                    mSongPlayer.start();

                    //Set up a Listener, to stop & release it once audio finished playing
                    mSongPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        //Navigation  Image Button.
        ImageButton navHome = findViewById(R.id.btn_home);
        ImageButton navElectro = findViewById(R.id.btn_electro);
        ImageButton navPopSong = findViewById(R.id.btn_pop);
        ImageButton navrock = findViewById(R.id.btn_rock);
        ImageButton navCinema = findViewById(R.id.btn_cinema);
        ImageButton navJazz = findViewById(R.id.btn_jazz);
        ImageButton navPotPourit = findViewById(R.id.btn_equalizer);

        //Navigate to Home Activity
        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgHome = new Intent(PopSongs.this, MainActivity.class);
                startActivity(btnImgHome);
            }
        });

        navElectro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgElec = new Intent(PopSongs.this, Electronica.class);
                startActivity(btnImgElec);
            }
        });
        navPopSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgPop = new Intent(PopSongs.this, PopSongs.class);
                startActivity(btnImgPop);
            }
        });

        navrock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgRock = new Intent(PopSongs.this, RocknRoll.class);
                startActivity(btnImgRock);
            }
        });

        navCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgCine = new Intent(PopSongs.this, Cinematic.class);
                startActivity(btnImgCine);
            }
        });

        navJazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgJazz = new Intent(PopSongs.this, JazzMusic.class);
                startActivity(btnImgJazz);
            }
        });

        navPotPourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgPotPourit = new Intent(PopSongs.this, PotPourit.class);
                startActivity(btnImgPotPourit);
            }
        });

    } // end onCreate

    //Release the mediaPlayer when the activity is Stop
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    //Clean up the media player by releasing it's resources
    private void releaseMediaPlayer() {
        //check if mediaplayer is not null, it might be busy
        if (mSongPlayer != null) {
            //go ahead Release its resources regardless of the state
            mSongPlayer.release();
            //set back to null,  longer needs it
            mSongPlayer = null;

            //Regardless of whether or not we were granted audio focus, abandon it.
            // This also unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudiofocusChangeListener);
        }
    }

}//end class