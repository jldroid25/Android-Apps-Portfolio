package com.nevictus.android.coolmix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SnhSportClubsFragment extends Fragment {


    public SnhSportClubsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container,false);

        //Arraylist for Southern NH, Sports & Country Clubs List
        ArrayList<Data> SNH_CClubs =  new ArrayList<>();

        //Adding our Sports & Country Clubs Section
        SNH_CClubs .add(new Data(R.drawable.hackey,
                getString(R.string.spTitile1),
                getString(R.string.spAddr1),
                getString(R.string.spSite1),
                getString(R.string.spDesc1)
        ));
        SNH_CClubs .add(new Data(R.drawable.baseball,
                getString(R.string.spTitile2),
                getString(R.string.spAddr2),
                getString(R.string.spSite2),
                getString(R.string.spDesc2)
        ));
        SNH_CClubs .add(new Data(R.drawable.derryfieldpark1,
                getString(R.string.spTitle3),
                getString(R.string.spAddr3),
                getString(R.string.spSite3),
                getString(R.string.spDesc3)
        ));
        SNH_CClubs .add(new Data(R.drawable.countryclub1,
                getString(R.string.spTitle4),
                getString(R.string.spAddr4),
                getString(R.string.spSite4),
                getString(R.string.spDesc4)
        ));
        SNH_CClubs .add(new Data(R.drawable.baseball,
                getString(R.string.spTitle5),
                getString(R.string.spAddr5),
                getString(R.string.spSite5),
                getString(R.string.spDesc5)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhDataAdapter = new DataAdapter(getActivity(), SNH_CClubs );
        ListView snhSportListView =  rootView.findViewById(R.id.list);
        snhSportListView.setAdapter(snhDataAdapter);

        return rootView;
    }
}