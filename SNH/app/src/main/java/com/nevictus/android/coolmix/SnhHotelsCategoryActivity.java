package com.nevictus.android.coolmix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SnhHotelsCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snh_data__category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SnhHotelsFragment()).commit();
    }
}
