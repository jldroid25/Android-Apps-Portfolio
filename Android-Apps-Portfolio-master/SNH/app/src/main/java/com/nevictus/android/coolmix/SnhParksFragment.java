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
public class SnhParksFragment extends Fragment {


    public SnhParksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container,false);

        //Arraylist for Southern NH, Sports & Country Clubs List
        ArrayList<Data> SNH_Walking =  new ArrayList<>();

        //Adding our parks
        SNH_Walking.add(new Data(R.drawable.park1,
                getString(R.string.pTitle1),
                getString(R.string.pAddr1),
                getString(R.string.pSite1),
                getString(R.string.pDesc1)
        ));
        SNH_Walking.add(new Data(R.drawable.park2,
                getString(R.string.pTitle2),
                getString(R.string.pAddr2),
                getString(R.string.pSite2),
                getString(R.string.pDesc2)
        ));
        SNH_Walking.add(new Data(R.drawable.park3,
                getString(R.string.pTitle3),
                getString(R.string.pAddr3),
                getString(R.string.pSite3),
                getString(R.string.pDesc3)
        ));
        SNH_Walking.add(new Data(R.drawable.park1,
                getString(R.string.pTitle4),
                getString(R.string.pAddr4),
                getString(R.string.pSite4),
                getString(R.string.pDesc4)
        ));
        SNH_Walking.add(new Data(R.drawable.derryfieldpark1,
                getString(R.string.pTitle5),
                getString(R.string.pAddr5),
                getString(R.string.pSite6),
                getString(R.string.pDesc5)
        ));
        //Set ArrayAdapter & ListView.
        DataAdapter snhWalkingAdapter = new DataAdapter(getActivity(), SNH_Walking);
        ListView snhWalkingListView =  rootView.findViewById(R.id.list);
        snhWalkingListView.setAdapter(snhWalkingAdapter);

        return rootView;
    }
}