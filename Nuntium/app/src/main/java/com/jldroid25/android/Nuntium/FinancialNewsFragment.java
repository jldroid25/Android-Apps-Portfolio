package com.jldroid25.android.Nuntium;

import android.content.Context;
import android.content.Intent;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class FinancialNewsFragment extends Fragment implements LoaderCallbacks<List<FinancialNews>> {

    // Required empty public constructor
    public FinancialNewsFragment() { }

    //Constant value for the financialNews loader ID. We can choose any integer.
    //This really only comes into play if you're using multiple loaders.
    private static final int NEWS_LOADER_ID = 1;

    // In order to access and modify the instance of the FinancialNewsAdapter.
    // We need this global variable so that we can update the data set within
    // the FinancialNewsAdapter. Later on we can update the ListView in onLoadFinish().
    private FinancialNewsAdapter newsAdapter;

    // TextView that is displayed when the list is empty
    private TextView mEmptyStateTextView;

    //URL for the Financial News Data from theGuardian api
    private static final String GUARDIAN_NEWS_REQUEST_URL ="https://content.guardianapis.com/search?";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fn_activity, container,
                false);

        // Find a reference to the ListView in the layout
        ListView newsListView = rootView.findViewById(R.id.list);

        //Create a new adapter that takes an empty list of financial news as input
        newsAdapter = new FinancialNewsAdapter(getActivity(), new ArrayList<FinancialNews>());
        //Text to be displayed when no data is available on our list.
        mEmptyStateTextView = rootView.findViewById(R.id.empty_view);
        //Set the Empty TextView
        newsListView.setEmptyView(mEmptyStateTextView);
        // Set the adapter on the ListView to populate the list in the user interface
        newsListView.setAdapter(newsAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news article.
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                // Find the current news article that was clicked on
                FinancialNews currentFinancialNews = newsAdapter.getItem(position);
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri financialNewsUri = Uri.parse(currentFinancialNews.getUrl());
                // Create a new implicit intent to view the financial news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, financialNewsUri);
                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        }); // end setOnClickListener

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            /*
             * Initialize the loader. Pass in the int ID constant defined above
             * and pass in null for the bundle. Pass in this activity for the
             * LoaderCallbacks parameter (which is valid because this activity implements
             * the LoaderCallbacks interface).
             * */
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);

        } else {
            // Show loading indicator because the data has been loaded
            View loadingIndicator = rootView.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.VISIBLE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
        return rootView;
    } //End onCreateView

    @Override
    public Loader<List<FinancialNews>> onCreateLoader(int id, Bundle args) {

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        //getString retrieves a String value from the preferences.
        //The second parameter is the default value for this preference.
        String pageSize = sharedPrefs.getString(
                getString(R.string.settings_min_articles_key),
                getString(R.string.settings_min_articles_default));

        // For the orderBy String Preference
        String orderBy = sharedPrefs.getString(getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default));

        // For the Production Office String Preference
        String prodOffice = sharedPrefs.getString(getString(R.string.settings_production_office_key),
                getString(R.string.settings_production_office_default));

        // parse breaks apart the URI string that's passed into its parameter
        Uri baseUri = Uri.parse(GUARDIAN_NEWS_REQUEST_URL);

        //buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append query parameter and its value.
        uriBuilder.appendQueryParameter("section","business");
        uriBuilder.appendQueryParameter("page-size", pageSize);
        uriBuilder.appendQueryParameter("show-tags","contributor");
        uriBuilder.appendQueryParameter("show-fields","bodyText");
        uriBuilder.appendQueryParameter("order-by", orderBy);
        uriBuilder.appendQueryParameter("production-office", prodOffice);
        uriBuilder.appendQueryParameter("api-key","d45f75ec-99d7-47ae-ade8-9c2118745901");


        // Return the completed uri String
        return new FinancialNewsLoader(getActivity(), uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<FinancialNews>> loader, List<FinancialNews> financialNews) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = getView().findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        //Set the empty state text to be display "No data is found"
        mEmptyStateTextView.setText(R.string.fail_message);

        // Clear the adapter of previous news data
        newsAdapter.clear();

        // If there is a valid list of  news,then add them to the adapter's data set.
        // This will trigger the ListView to update.
        if (financialNews != null && !financialNews.isEmpty()) {
            newsAdapter.addAll(financialNews);
        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<FinancialNews>> loader) {
        // Loader reset, so we can clear out our existing data.
        newsAdapter.clear();
    }
} //end class