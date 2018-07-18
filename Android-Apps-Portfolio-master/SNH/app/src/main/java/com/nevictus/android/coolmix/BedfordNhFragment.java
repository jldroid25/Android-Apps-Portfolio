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
public class BedfordNhFragment extends Fragment {


    public BedfordNhFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.city_list, container,
                false);

        //Arraylist for Bedford, NH Cities
        ArrayList<Cities> bedfordNhCities = new ArrayList<Cities>();

        bedfordNhCities.add(new Cities(R.drawable.bedfordnh,getString(R.string.bedfordnhTitle),
                getString(R.string.bedfordInfoText)));

        //Using ArrayAdapter & ListView  to Recycle the Views
        CityAdapter bedfordCityAdapter = new CityAdapter(getActivity(), bedfordNhCities);
        ListView bedfordCityListView =  rootView.findViewById(R.id.list);
        bedfordCityListView.setAdapter(bedfordCityAdapter);

        return rootView;
    }
}
