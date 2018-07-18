package com.nevictus.android.coolmix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnhAirportFragment extends Fragment {

    public SnhAirportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.snh_airport__category, container, false);
        //display airport title
        TextView airportTitle = view.findViewById(R.id.mhtTitleId);
        airportTitle.getText();
        //display airport Image
        ImageView airportImg = view.findViewById(R.id.mht_airportImgId);
        airportImg.getDrawable();
        //display airport Website
        TextView airportWebsite = view.findViewById(R.id.mht_websiteId);
        airportWebsite.getText();
        //display airport Address
        TextView airportAddress = view.findViewById(R.id.mht_addressId);
        airportAddress.getText();
        //display airport information
        TextView airportInfo = view.findViewById(R.id.mht_infoTxtId);
        airportInfo.getText();

        return view;
    }
}