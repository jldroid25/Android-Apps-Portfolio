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

public class PotPourit extends AppCompatActivity {

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
        final ArrayList<Songs> potPouritSongs = new ArrayList<Songs>();

        //Shuffling every song
        potPouritSongs.add(new Songs(R.drawable.electropic5, "Endless Motion",
                " Jumpy electronica tape.",
                "DJ Berny", "2017", R.raw.electro5));

        potPouritSongs.add(new Songs(R.drawable.cinepic1, "Epic",
                "Epic Theme song great for battle scenes.",
                "DJ Clue", "2002", R.raw.cinesong1));

        potPouritSongs.add(new Songs(R.drawable.rocksong5, "Actionable",
                " Now you see no butter no bread it's that simple.",
                "The Window", "2017", R.raw.rocksong5));

        potPouritSongs.add(new Songs(R.drawable.jazzpic2, "Jazzy Frenchy",
                "Paris Monmartre Jazz Song.",
                "Duke Earl.", "2018", R.raw.jazz2));

        potPouritSongs.add(new Songs(R.drawable.electropic4, "Scify",
                "Spacey Electronica.",
                "DJ Swifty", "2018", R.raw.electro4));

        potPouritSongs.add(new Songs(R.drawable.cinepic4, "Tommorow",
                "The think man Theme song.",
                "DJ Manchester", "2018", R.raw.cinesong4));

        potPouritSongs.add(new Songs(R.drawable.poppic5, "Clear Day",
                " Coconut water got nothing on this track.",
                "Pure H2O", "2017", R.raw.popsong5));

        potPouritSongs.add(new Songs(R.drawable.jazzpic1, "All That Jazz",
                " Monsieur Parisien Qui aime le jazz comme un fou.",
                "Marselist L", "2017", R.raw.jazz1));

        potPouritSongs.add(new Songs(R.drawable.cinepic5, "Slow Motion",
                " Lower the mood theme song electronica tape.",
                "DJ Louis XXIV", "2017", R.raw.cinesong5));

        potPouritSongs.add(new Songs(R.drawable.rockpic2, "Happy Rock",
                "Rock til you drop says the beatle.",
                "Ramonez", "2017", R.raw.electro2));

        potPouritSongs.add(new Songs(R.drawable.electropic1, "Summer",
                "Soft electronic Dance Song.",
                "DJ Bensound", "2018", R.raw.electro1));

        potPouritSongs.add(new Songs(R.drawable.jazzpic5, "L'amour de Pigale",
                " 7eme Arroundisment jazz music .",
                "Aznavour C", "2018", R.raw.jazz5));

        potPouritSongs.add(new Songs(R.drawable.rockpic4, "Rumble",
                "The British are coming!  rumbling the Knight rider.",
                "Them 2", "2018", R.raw.rocksong4));

        potPouritSongs.add(new Songs(R.drawable.cinepic2, "Memories",
                "For the country nostalgic romantics.",
                "DJ Vice", "1999", R.raw.cinesong2));

        potPouritSongs.add(new Songs(R.drawable.poppic1, "Creative Mind",
                "Inspiring Pop song music featuring guitars & more.",
                "Jackson M", "2018", R.raw.popsong1));

        potPouritSongs.add(new Songs(R.drawable.cinepic3, "Better Days",
                "Mood lifting Flick for the downers.",
                "DJ Hollywood", "2016", R.raw.cinesong3));

        potPouritSongs.add(new Songs(R.drawable.jazzpic3, "Lounge",
                "Cool Jazz lounge gem.",
                "Percy J", "2017", R.raw.jazz3));

        potPouritSongs.add(new Songs(R.drawable.rockpic3, "Going Higher",
                "The movement is real right now.",
                "Yoo Fighter", "2016", R.raw.rocksong1));

        potPouritSongs.add(new Songs(R.drawable.electropic2, "Dubstep",
                "Cool electronic mix.",
                "DJ Bensound", "2017", R.raw.electro2));

        potPouritSongs.add(new Songs(R.drawable.poppic2, "Little Idea",
                "la la laluba pop song.",
                "Tomato Cake", "2017", R.raw.popsong2));

        potPouritSongs.add(new Songs(R.drawable.electropic3, "Moose",
                "Melo electronic step.",
                "DJ Dutchy", "2016", R.raw.electro3));

        potPouritSongs.add(new Songs(R.drawable.jazzpic4, "The Jazz Piano",
                "Piano Jazzy tunes for the music lovers.",
                "Amy W.", "2016", R.raw.jazz4));

        potPouritSongs.add(new Songs(R.drawable.poppic3, "Hey, Hey",
                "Chearful & fun track featuring piane & more.",
                "Timbersea J", "2016", R.raw.popsong3));

        potPouritSongs.add(new Songs(R.drawable.rockpic1, "A New Beginning",
                "The Rock song the pastor warns you about.",
                "Zeplin L", "2018", R.raw.rocksong1));

        potPouritSongs.add(new Songs(R.drawable.poppic4, "Energy",
                "Motivational music track to get you going.",
                "Mozart B", "2018", R.raw.popsong4));

        //Using ArrayAdapter & ListView  to Recycle the Views
        SongAdapter potPouritAdapter = new SongAdapter(this, potPouritSongs);
        ListView potPouritSongListView = findViewById(R.id.list);
        potPouritSongListView.setAdapter(potPouritAdapter);

        //Click to play the Song
        potPouritSongListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Release the MediaPlayer if exists, we're about to play a different song
                releaseMediaPlayer();

                Songs audio = potPouritSongs.get(position);

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

                    mSongPlayer = MediaPlayer.create(PotPourit.this, audio.getAudioFile());
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
                Intent btnImgHome = new Intent(PotPourit.this, MainActivity.class);
                startActivity(btnImgHome);
            }
        });

        navElectro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgElec = new Intent(PotPourit.this, Electronica.class);
                startActivity(btnImgElec);
            }
        });
        navPopSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgPop = new Intent(PotPourit.this, PopSongs.class);
                startActivity(btnImgPop);
            }
        });

        navrock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgRock = new Intent(PotPourit.this, RocknRoll.class);
                startActivity(btnImgRock);
            }
        });

        navCinema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgCine = new Intent(PotPourit.this, Cinematic.class);
                startActivity(btnImgCine);
            }
        });

        navJazz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgJazz = new Intent(PotPourit.this, JazzMusic.class);
                startActivity(btnImgJazz);
            }
        });

        navPotPourit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent btnImgPotPourit = new Intent(PotPourit.this, PotPourit.class);
                startActivity(btnImgPotPourit);
            }
        });

    } //end oncreate

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
}// end class
