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
public class ManchesterNhFragment extends Fragment {

    public ManchesterNhFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.city_list, container,
                false);

        //Arraylist for Manchester Cities
        ArrayList<Cities> nhCities = new ArrayList<Cities>();

        nhCities.add(new Cities(R.drawable.manchester1, getString(R.string.manchesterTitle),
                getString(R.string.manchesterInfoText)));

        //Using ArrayAdapter & ListView  to Recycle the Views
        CityAdapter manchesterCityAdapter = new CityAdapter(getActivity(), nhCities);
        ListView manchesterCityListView =  rootView.findViewById(R.id.list);
        manchesterCityListView.setAdapter(manchesterCityAdapter);

        return rootView;
    }
}
