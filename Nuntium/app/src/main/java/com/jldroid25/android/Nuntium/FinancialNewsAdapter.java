package com.jldroid25.android.Nuntium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FinancialNewsAdapter extends ArrayAdapter<FinancialNews> {

    //Custom Constructor
    public FinancialNewsAdapter(Context context, List<FinancialNews> financialNews) {
        super(context, 0, financialNews);
    }

    //Implementing View Holder Pattern to better optimize memory
    class fnNewsViewHolder {
        TextView secName;
        TextView articleWebTitle;
        TextView datePublish;
        TextView newsWriter;
        TextView newsBodyText;

        //Constructor initialize the objects here to prevent
        // repeated/expensive calls by findViewById()
        fnNewsViewHolder(View v) {
            secName = v.findViewById(R.id.sectionName);
            articleWebTitle = v.findViewById(R.id.webTitle);
            articleWebTitle = v.findViewById(R.id.webTitle);
            datePublish = v.findViewById(R.id.datePublish);
            newsWriter = v.findViewById(R.id.writerId);
            newsBodyText = v.findViewById(R.id.bodyText);
        }
    }

    //To provide the view & inflate it if not already exists with new article(s)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Creating a holder object
        fnNewsViewHolder holder = null;

        //If we're creating new views go ahead & inflate as normal
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.fn_list_item, parent, false);

            //Initialized the holder
            holder = new fnNewsViewHolder(convertView);

            //Let's store the holder to be reuse when we are recycling items
            convertView.setTag(holder);

            //But If we're only recycling, just reuse the already created holder object(s)
        } else {
            holder = (fnNewsViewHolder) convertView.getTag();
        }
        //find the item position
        FinancialNews currentNews = getItem(position);

        //Retrieve & set the article Section Name
        holder.secName.setText(currentNews.getSectionName());
        //Retrieve & set the article WebTitle
        holder.articleWebTitle.setText(currentNews.getWebTitle());
        //Retrieve & set the article Published Date
        holder.datePublish.setText(currentNews.getPublicationDate());
        //Retrieve & set the author
        holder.newsWriter.setText(currentNews.getWriterName());
        //Retrieve & set the full article body Text
        holder.newsBodyText.setText(currentNews.getArticleBodyText());

        return convertView;
    } // end getView
} // end class