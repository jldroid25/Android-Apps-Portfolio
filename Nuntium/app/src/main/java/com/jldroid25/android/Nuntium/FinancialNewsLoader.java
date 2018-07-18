package com.jldroid25.android.Nuntium;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;

public class FinancialNewsLoader extends AsyncTaskLoader<List<FinancialNews>> {

    //The Query URL
    private String newsUrl;

    //To construct a new FinancialNewsLoader with the activity's context
    public FinancialNewsLoader(Context context, String url) {
        super(context);
        // and the url to load the data from.
        newsUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    //This is our background thread
    @Override
    public List<FinancialNews> loadInBackground() {
        if (newsUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of news article.
        List<FinancialNews> financialNews = FinancialNewsQueryUtils.fetchFinancialNewsDataString(newsUrl);
        return financialNews;
    }
}