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
public class MerrimackNhFragment extends Fragment {

    public MerrimackNhFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.city_list, container,
                false);

        //Arraylist of Merrimack NH Cities
        ArrayList<Cities> merrimackdNhCities = new ArrayList<Cities>();

        merrimackdNhCities.add(new Cities(R.drawable.merrimack,getString(R.string.merrimackTitle),
                getString(R.string.MerrimackInfoText)
        ));
        //Using ArrayAdapter & ListView  to Recycle the Views
        CityAdapter merrimackCityAdapter = new CityAdapter(getActivity(), merrimackdNhCities);
        ListView merrimackCityListView =  rootView.findViewById(R.id.list);
        merrimackCityListView.setAdapter(merrimackCityAdapter);

        return rootView;
    }
}