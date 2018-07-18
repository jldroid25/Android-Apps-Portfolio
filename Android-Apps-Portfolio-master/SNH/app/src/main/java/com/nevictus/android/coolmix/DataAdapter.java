package com.nevictus.android.coolmix;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class DataAdapter extends ArrayAdapter <Data> {

    //Custom Constructor
    public DataAdapter(Activity context, ArrayList <Data> Data){
        super(context, 0, Data);
    }

    //Implementing View Holder Pattern to better optimize memory
    class dataListViewHolder {
        ImageView DataImg;
        TextView tiTleTxtView;
        TextView addressTxtView;
        TextView websiteTxtView;
        TextView descTxtView;

        //Constructor initialize the objects here to prevent
        // repeated/expensive calls on findViewById()
        dataListViewHolder(View v) {
             DataImg = v.findViewById(R.id.museumImageView);
            tiTleTxtView = v.findViewById(R.id.museumTitleView);
            addressTxtView = v.findViewById(R.id.museumAddressView);
            websiteTxtView = v.findViewById(R.id.museumSiteView);
            descTxtView = v.findViewById(R.id.museumDescriptionView);
        }
    }

    //To provide the view & inflate it if not already exists with new songs
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View dataItemView = convertView;

        dataListViewHolder holder = null;

        //If we're creating new views go ahead & inflate as normal
        if(dataItemView == null) {
            dataItemView = LayoutInflater.from(getContext()).inflate(R.layout.data_item, parent,
                    false);

            //Initialized the holder
            holder = new dataListViewHolder(dataItemView);

            //Let's store the holder to be reuse when we are recycling items
            dataItemView.setTag(holder);

            //If we're only recycling, just reuse the already created holder object(s)
        } else {
            holder = (dataListViewHolder) dataItemView.getTag();
        }

        //find the item position
        Data singleData = getItem(position);

        //Check if an image is available
        if (singleData.hasImage()) {
            holder.DataImg.setImageResource(singleData.getDataImgId());
            //Make it visible
            holder.DataImg.setVisibility(View.VISIBLE);

        } else {
            //hide the imageView
            holder.DataImg.setVisibility(View.GONE);
        }

        //Retrieve & set the museum title
        holder.tiTleTxtView.setText(singleData.getDataTitle());
        //Retrieve & set the museum address
        holder.addressTxtView.setText(singleData.getDataAddress());
        //Retrieve & set the museum website
        holder.websiteTxtView.setText(singleData.getDataSite());
        //Retrieve & set the museum website
        holder.descTxtView.setText(singleData.getDataDescription());

        return dataItemView;
    }//end getView()
}
