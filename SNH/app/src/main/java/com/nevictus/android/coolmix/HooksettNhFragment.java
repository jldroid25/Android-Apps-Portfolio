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
public class HooksettNhFragment extends Fragment {

    public HooksettNhFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.city_list, container,
                false);

        //Arraylist for Hooksett NH
        ArrayList<Cities> hooksettNhCities = new ArrayList<Cities>();

        hooksettNhCities.add(new Cities(R.drawable.hooksett2,getString(R.string.hooksettTitle),
                getString(R.string.hooksettInfo)
        ));
        //Using ArrayAdapter & ListView  to Recycle the Views
        CityAdapter hooksettCityAdapter = new CityAdapter(getActivity(), hooksettNhCities);
        ListView hooksettCityListView =  rootView.findViewById(R.id.list);
        hooksettCityListView.setAdapter(hooksettCityAdapter);

        return rootView;
    }
}