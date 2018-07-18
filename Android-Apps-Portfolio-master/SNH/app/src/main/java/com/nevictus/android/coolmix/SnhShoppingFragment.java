package com.nevictus.android.coolmix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class SnhShoppingFragment extends Fragment {

    public SnhShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container, false);

        //Arraylist for Shopping
        ArrayList<Data> SNH_Shopping = new ArrayList<>();

        //Adding our shops
        SNH_Shopping.add(new Data(R.drawable.shopping1,
                getString(R.string.shTitle1),
                getString(R.string.shAddr1),
                getString(R.string.shSite1),
                getString(R.string.shDesc1)
        ));
        SNH_Shopping.add(new Data(R.drawable.shopping2,
                getString(R.string.shTitle2),
                getString(R.string.shAddr2),
                getString(R.string.shSite2),
                getString(R.string.shDesc2)
        ));
        SNH_Shopping.add(new Data(R.drawable.shopping3,
                getString(R.string.shTitle3),
                getString(R.string.shAddr3),
                getString(R.string.shSite3),
                getString(R.string.shDesc3)
        ));
        SNH_Shopping.add(new Data(R.drawable.shopping2,
                getString(R.string.shTitle4),
                getString(R.string.shAddr4),
                getString(R.string.shSite4),
                getString(R.string.shDesc4)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhShoppingAdapter = new DataAdapter(getActivity(), SNH_Shopping);
        ListView snhShoppingListView = rootView.findViewById(R.id.list);
        snhShoppingListView.setAdapter(snhShoppingAdapter);

        return rootView;
    }
}