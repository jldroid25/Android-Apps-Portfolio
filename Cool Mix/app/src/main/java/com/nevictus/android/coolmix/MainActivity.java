package com.nevictus.android.coolmix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Object References
        ImageView electronica = findViewById(R.id.electroImg);
        ImageView cinematicSong = findViewById(R.id.cinemaImg);
        ImageView popSong = findViewById(R.id.popImg);
        ImageView jazzMusic = findViewById(R.id.jazzImg);
        ImageView rockNRoll = findViewById(R.id.rockImg);
        ImageView potPouritPic = findViewById(R.id.potPouritImg);

        //Navigate to Electronica Activity
        electronica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent electro = new Intent(MainActivity.this, Electronica.class);
                startActivity(electro);
                Toast.makeText(view.getContext(), "Royalty Free Art & Music by:\n www.bensound.com",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Navigate to Cinematic Activity
        cinematicSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cinema = new Intent(MainActivity.this, Cinematic.class);
                startActivity(cinema);
                Toast.makeText(view.getContext(), "Royalty Free Art & Music by:\n www.bensound.com",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Navigate to PopSongs Activity
        popSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent popSong = new Intent(MainActivity.this, PopSongs.class);
                startActivity(popSong);
                Toast.makeText(view.getContext(), "Royalty Free Art & Music by:\n www.bensound.com",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Navigate to  Jazz Activity
        jazzMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jazzMusic = new Intent(MainActivity.this, JazzMusic.class);
                startActivity(jazzMusic);
                Toast.makeText(view.getContext(), "Royalty Free Art & Music by:\n www.bensound.com",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Navigate to  Rock n Roll Activity
        rockNRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rockNroll = new Intent(MainActivity.this, RocknRoll.class);
                startActivity(rockNroll);
                Toast.makeText(view.getContext(), "Royalty Free Art & Music by:\n www.bensound.com",
                        Toast.LENGTH_SHORT).show();
            }
        });

        //Navigate to  Pot Pourit Activity
        potPouritPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent potPourit = new Intent(MainActivity.this, PotPourit.class);
                startActivity(potPourit);
                Toast.makeText(view.getContext(), "Royalty Free Art & Music by:\n www.bensound.com",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }//end on create
}// end class