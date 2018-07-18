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
public class SnhWalkingFragment extends Fragment {

    public SnhWalkingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container, false);

        //Arraylist for Southern NH, Walking Tour
        ArrayList<Data> SNH_Walking = new ArrayList<>();

        //Adding our Walking Itinerary
        SNH_Walking.add(new Data(R.drawable.walking3,
                getString(R.string.wTitle1),
                getString(R.string.wAddr1),
                getString(R.string.wSite1),
                getString(R.string.wDesc1)
        ));
        SNH_Walking.add(new Data(R.drawable.walking1,
                getString(R.string.wTtile2),
                getString(R.string.wAddr2),
                getString(R.string.wSite2),
                getString(R.string.wDesc2)
        ));
        SNH_Walking.add(new Data(R.drawable.library1,
                getString(R.string.wTtile3),
                getString(R.string.wAdd3),
                " ",
                getString(R.string.wDesc3)
        ));
        SNH_Walking.add(new Data(R.drawable.park2,
                getString(R.string.wTitle4),
                getString(R.string.wAddr4),
                getString(R.string.wSite4),
                getString(R.string.wDesc4)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhWalkingAdapter = new DataAdapter(getActivity(), SNH_Walking);
        ListView snhWalkingListView = rootView.findViewById(R.id.list);
        snhWalkingListView.setAdapter(snhWalkingAdapter);

        return rootView;
    }
}