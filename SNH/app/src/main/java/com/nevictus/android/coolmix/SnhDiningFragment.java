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
public class SnhDiningFragment extends Fragment {


    public SnhDiningFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container,false);

        //Arraylist for dining Section
        ArrayList<Data> SNH_Dining =  new ArrayList<>();

        //Adding our dining Section
        SNH_Dining.add(new Data(R.drawable.dining1,
                getString(R.string.diningTitle1),
                getString(R.string.diningAddress1),
                getString(R.string.diningSite1),
                getString(R.string.diningDesc1)
        ));
        SNH_Dining.add(new Data(R.drawable.dining2,
                getString(R.string.diningTitle2),
                getString(R.string.diningAddr2),
                getString(R.string.diningSite2),
                getString(R.string.diningDesc2)
        ));
        SNH_Dining.add(new Data(R.drawable.dining1,
                getString(R.string.diningTitle3),
                getString(R.string.diningAddr3),
                getString(R.string.diningsite3),
                getString(R.string.diningDesc3)
        ));
        SNH_Dining.add(new Data(R.drawable.coffeeshop,
                getString(R.string.diningTitle4),
                getString(R.string.diningAddr4),
                getString(R.string.diningSite4),
                getString(R.string.diningDesc4)
        ));
        SNH_Dining.add(new Data(R.drawable.bakery,
                getString(R.string.diningTitle5),
                getString(R.string.diningAddr5),
                getString(R.string.diningSite5),
                getString(R.string.diningDesc5)
        ));
        SNH_Dining.add(new Data(R.drawable.brewery,
                getString(R.string.diningTitle6),
                getString(R.string.diningAddr6),
                getString(R.string.diningSite6),
                getString(R.string.diningDesc6)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhDiningAdapter = new DataAdapter(getActivity(), SNH_Dining);
        ListView snhDiningListView =  rootView.findViewById(R.id.list);
        snhDiningListView.setAdapter(snhDiningAdapter);

        return rootView;
    }
}