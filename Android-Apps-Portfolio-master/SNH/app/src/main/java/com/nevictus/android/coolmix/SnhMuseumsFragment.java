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
public class SnhMuseumsFragment extends Fragment {

    public SnhMuseumsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container, false);

        //Arraylist for Southern NH, Data
        ArrayList<Data> SNH_Museums = new ArrayList<>();

        //Adding our museums
        SNH_Museums.add(new Data(R.drawable.museum1,
                getString(R.string.mTitle1),
                getString(R.string.mAddr1),
                getString(R.string.mSite1),
                getString(R.string.mDesc1)
        ));
        SNH_Museums.add(new Data(R.drawable.museum5,
                getString(R.string.mTitle2),
                getString(R.string.mAddr2),
                getString(R.string.mSite2),
                getString(R.string.mDesc2)
        ));
        SNH_Museums.add(new Data(R.drawable.museum2,
                getString(R.string.mTitle3),
                getString(R.string.mAddr3),
                getString(R.string.msite3),
                getString(R.string.mDesc3)
        ));
        SNH_Museums.add(new Data(R.drawable.museum3,
                getString(R.string.mTitle4),
                getString(R.string.mAddr4),
                getString(R.string.mSite4),
                getString(R.string.mDesc4)
        ));
        SNH_Museums.add(new Data(R.drawable.museum4,
                getString(R.string.mTitle5),
                getString(R.string.mAddr5),
                getString(R.string.mSite5),
                getString(R.string.mDesc5)
        ));
        SNH_Museums.add(new Data(R.drawable.library1,
                getString(R.string.mTitle6),
                getString(R.string.mAddr6),
                getString(R.string.mSite6),
                getString(R.string.mDesc6)
        ));
        SNH_Museums.add(new Data(R.drawable.museum1,
                getString(R.string.mTitle7),
                getString(R.string.mAddr7),
                getString(R.string.mSite7),
                getString(R.string.mDesc7)
        ));
        SNH_Museums.add(new Data(R.drawable.museum3,
                getString(R.string.mTitle8),
                getString(R.string.mAddr8),
                getString(R.string.mSite8),
                getString(R.string.mDesc8)
        ));
        SNH_Museums.add(new Data(R.drawable.museum4,
                getString(R.string.mTitle9),
                getString(R.string.mAddr9),
                getString(R.string.mSite9),
                getString(R.string.mDesc9)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhDataAdapter = new DataAdapter(getActivity(), SNH_Museums);
        ListView snhMuseumListView = rootView.findViewById(R.id.list);
        snhMuseumListView.setAdapter(snhDataAdapter);
        return rootView;
    }
}