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
public class SnhHotelsFragment extends Fragment {

    public SnhHotelsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container,false);

        //Arraylist for Southern NH, Hotels
        ArrayList<Data> SNH_Hotels =  new ArrayList<>();

        //Adding our hotels
        SNH_Hotels.add(new Data(R.drawable.hotel1,
                getString(R.string.hTitle1),
                getString(R.string.hAddr1),
                getString(R.string.hsite1),
                getString(R.string.hDesc1)
        ));
        SNH_Hotels.add(new Data(R.drawable.hotel2,
                getString(R.string.htitle2),
                getString(R.string.hAddr2),
                getString(R.string.hSite2),
                getString(R.string.hdesc2)
        ));
        SNH_Hotels.add(new Data(R.drawable.hotel3,
                getString(R.string.hTitle3),
                getString(R.string.hAddr3),
                getString(R.string.hSite3),
                getString(R.string.hDesc3)
        ));
        SNH_Hotels.add(new Data(R.drawable.hotel4,
                getString(R.string.hTitle4),
                getString(R.string.hAddr4),
                getString(R.string.hSite4),
                getString(R.string.hDesc4)
        ));
        SNH_Hotels.add(new Data(R.drawable.hotel5,
                getString(R.string.hTitle5),
                getString(R.string.hAddr5),
                getString(R.string.hsite5),
                getString(R.string.hDesc5)
        ));
           SNH_Hotels.add(new Data(R.drawable.hotel2,
                getString(R.string.hTitle6),
                getString(R.string.hAddr6),
                getString(R.string.hSite6),
                getString(R.string.hDesc6)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhHotelsAdapter = new DataAdapter(getActivity(), SNH_Hotels);
        ListView snhHotelsListView =  rootView.findViewById(R.id.list);
        snhHotelsListView.setAdapter(snhHotelsAdapter);

        return rootView;
    }
}
