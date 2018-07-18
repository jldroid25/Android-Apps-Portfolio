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
public class SnhPublicLibraryFragment extends Fragment {

    public SnhPublicLibraryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container,false);

        //Arraylist for Southern NH, Public Library
        ArrayList<Data> SNH_Library =  new ArrayList<>();

        //Adding our Libraries
        SNH_Library.add(new Data(R.drawable.library1,
                getString(R.string.libTitle1),
                getString(R.string.libAddr1),
                getString(R.string.libSite1),
                getString(R.string.libDesc1)
        ));
        SNH_Library.add(new Data(R.drawable.library2,
                getString(R.string.libTitle2),
                getString(R.string.libAddr2),
                getString(R.string.libSite2),
                getString(R.string.libDesc2)
        ));
        SNH_Library.add(new Data(R.drawable.library3,
                getString(R.string.libTitle3),
                getString(R.string.libAddr3),
                getString(R.string.libSite3),
                getString(R.string.libDesc4)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhLibraryAdapter = new DataAdapter(getActivity(), SNH_Library );
        ListView snhLibraryListView =  rootView.findViewById(R.id.list);
        snhLibraryListView.setAdapter(snhLibraryAdapter);

        return rootView;
    }
}