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
public class SnhPerformingArtsFragment extends Fragment {

    public SnhPerformingArtsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.data_item, container,false);

        //Arraylist for Southern NH, Performing arts
        ArrayList<Data> SNH_PArts =  new ArrayList<>();

        //Adding our Performing Arts
        SNH_PArts.add(new Data(R.drawable.theatre1,
                getString(R.string.paTitle1),
                getString(R.string.paAddr1),
                getString(R.string.paSite1),
                getString(R.string.paDesc1)
        ));
        SNH_PArts .add(new Data(R.drawable.theatre2,
                getString(R.string.paTitle2),
                getString(R.string.paAddr2),
                getString(R.string.paSite2),
                getString(R.string.paDesc2)
        ));
        SNH_PArts .add(new Data(R.drawable.music1,
                getString(R.string.paTitle3),
                getString(R.string.paAddr3),
                getString(R.string.paSite3),
                getString(R.string.paDesc3)
        ));
        SNH_PArts .add(new Data(R.drawable.theatre3,
                getString(R.string.paTitle4),
                getString(R.string.paAddr4),
                getString(R.string.paSite4),
                getString(R.string.paDesc4)
        ));

        //Set ArrayAdapter & ListView.
        DataAdapter snhPArtsAdapter = new DataAdapter(getActivity(), SNH_PArts );
        ListView snhPArtsListView =  rootView.findViewById(R.id.list);
        snhPArtsListView.setAdapter(snhPArtsAdapter);

        return rootView;
    }
}