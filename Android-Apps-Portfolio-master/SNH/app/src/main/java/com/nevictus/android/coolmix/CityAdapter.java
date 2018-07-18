package com.nevictus.android.coolmix;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CityAdapter extends ArrayAdapter <Cities> {

    //Custom Constructor
    public CityAdapter(Activity context, ArrayList<Cities> Cities){
        super(context, 0, Cities);
    }

    //To provide the view & inflate it if not already exists with new city
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        //Implementing View Holder Pattern to better optimize memory
        class cityListViewHolder {
            ImageView cityImg;
            TextView tiTleTxtView;
            TextView descTxtView;

            //Constructor initialize the objects here to prevent
            // repeated/expensive calls on findViewById()
            cityListViewHolder(View v) {
                cityImg = v.findViewById(R.id.cityImage);
                tiTleTxtView = v.findViewById(R.id.cityName);
                descTxtView = v.findViewById(R.id.city_description);
            }
        }

        View cityItemView = convertView;

        cityListViewHolder holder = null;

        //If we're creating new views go ahead & inflate as normal
        if(cityItemView == null) {
            cityItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,
                    false);

            //Initialized the holder
            holder = new cityListViewHolder(cityItemView);

            //Let's store the holder to be reuse when we are recycling items
            cityItemView.setTag(holder);

            //If we're only recycling, just reuse the already created holder object(s)
        } else {
            holder = (cityListViewHolder) cityItemView.getTag();
        }

        //find the item position
        Cities singleCity = getItem(position);

        //Check if an image is available
        if (singleCity.hasImage()) {
            holder.cityImg.setImageResource(singleCity.getCityImgId());
            //Make it visible
            holder.cityImg.setVisibility(View.VISIBLE);

        } else {
            //hide the imageView
            holder.cityImg.setVisibility(View.GONE);
        }

        //Retrieve & set the city's title
        holder.tiTleTxtView.setText(singleCity.getCityName());
        //Retrieve & set the city's Description
        holder.descTxtView.setText(singleCity.getDescription());

        return cityItemView;
    }//end getView()
}//end class