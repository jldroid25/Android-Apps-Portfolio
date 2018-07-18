package com.jldroid25.android.Nuntium;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public final class FinancialNewsQueryUtils {

    private static final String LOG_TAG = FinancialNewsQueryUtils.class.getSimpleName();
    //Global variables for magic Numbers
    private static Integer READ_TIME_OUT = 10000;
    private static Integer CONNECT_TIME_OUT = 15000;
    private static Integer SLEEP_THREAD = 2000;

    //Required private constructor to protect our object from outside changes
    private FinancialNewsQueryUtils() {
    }

    /* Return new URL object from the given string URL */
    private static URL createURL(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /* Make an HTTP Request to the given URL and Return a String as the response. */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        //If the URL is null then return early
        if (url == null) {
            return jsonResponse;
        }

        //Create a url connection Object to start connection process
        HttpURLConnection urlConnection = null;
        //Create an InputStream  Object to get the low computer binary codes/bytes or 0 & 1
        InputStream inputStream = null;

        //Handling the connection Exception here
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            // in milliseconds, 1 seconds to read
            urlConnection.setReadTimeout(READ_TIME_OUT);
            //in milliseconds, 1.5 second to connect
            urlConnection.setConnectTimeout(CONNECT_TIME_OUT);
            //We making a "GET" request to the server
            urlConnection.setRequestMethod("GET");
            //Now let's make the connection
            urlConnection.connect();

            // If status equal 200, read the inputStream of the binary data & parse the response.
            //using HttpURLConnection.HTTP_OK which has default status code == 200
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                //readFromStream() to be defined below
                jsonResponse = readFromStream(inputStream);
            } else {
                //else display the fail response code.
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the financial News JSON results.", e);

            //In the even of a thrown Exception execute this block of code
        } finally {
            //disconnect if connection is not empty/null
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            //close the inputStream if it's not null
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //Convert the  InputStream into a String which contains the
    //whole JSON response from the server.

    private static String readFromStream(InputStream inputStream) throws IOException {

        //To create our strings
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {

            //For our binary byte codes (0 & 1) reader in UTF-8 (default)character set format
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            //For reading or putting all the small byte-codes into bigger string pieces
            BufferedReader reader = new BufferedReader(inputStreamReader);
            //Let's now read the built string(s) line by line
            String line = reader.readLine();

            //A quick while loop to iterate through all possible existing  code line(s)
            while (line != null) {
                //start adding the  line(s) in the a string array;
                output.append(line);
                //reade them now
                line = reader.readLine();
            }
        }
        // convert our low computer byte codes into string
        return output.toString();
    }

    //Return a list of news objects that has been built up from
    //parsing a given JSON response.

    private static List<FinancialNews> extractFeatureFromJson(String financialNewsJSON) {

        //If the json String is empty or null then return early
        if (TextUtils.isEmpty(financialNewsJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding fnNews to
        List<FinancialNews> fnNews = new ArrayList<>();

        // Try to parse the JSON RESPONSE String. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown
        // so the app doesn't crash, & print the error message to the logs

        try {
            //Create a json Object from a JSON RESPONSE string
            JSONObject baseJsonResponse = new JSONObject(financialNewsJSON);

            // Extract the JSONArray associated JSONObject with the key called "response",
            // and the JSONArray key results
            JSONArray financialNewsArray = baseJsonResponse.getJSONObject("response").getJSONArray("results");

            // For each news in the financialNewsArray, create an news object
            for (int i = 0; i < financialNewsArray.length(); i++) {

                // Get a single news at position i within the list of fnNews
                JSONObject singleResult = financialNewsArray.getJSONObject(i);

                // Extract the value for the key called "sectionName"
                String fnSectionName = singleResult.optString("sectionName");
                // Extract the value for the key called "Web Title"
                String fnWebTitle = singleResult.optString("webTitle");
                // Extract the value for the key called "Web Publication Date"
                String fnDate = singleResult.optString("webPublicationDate");
                String fnUrl = singleResult.optString("webUrl");

                //Remove unwanted time zone characters & add some space.
                String cut_T = fnDate;
                fnDate = cut_T.replace("T", "\nTime: \t").trim();
                String cut_Z = fnDate;
                fnDate = cut_Z.replace("Z", "").trim();

                //For the Fields section to retrieve the article's body text
                String fnBodyText = singleResult.getJSONObject("fields").optString("bodyText");

                //To Separate the author name from the obtain news Title from the API
                JSONArray newsTagArray = singleResult.getJSONArray("tags");

                //Let's store the writer's name in this variable
                String fnWriterName;

                if (newsTagArray.length() == 1) {
                    JSONObject fnWriterTag = (JSONObject) newsTagArray.get(0);
                    fnWriterName = fnWriterTag.optString("webTitle");
                } else {
                    fnWriterName = " ";
                }

                // Create a new financial News object with the magnitude, location, time,
                // and url from the JSON response.
                FinancialNews financialNews = new FinancialNews(fnSectionName, fnWebTitle, fnDate,
                        fnUrl, fnWriterName, fnBodyText);

                //Add the new financial News to the list of fnNews.
                fnNews.add(financialNews);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("FinancialNewsQueryUtils", "Problem parsing the financial news JSON results", e);
        }
        //Return the list of fnNews
        return fnNews;

    } //end ExtractFeatureFromJson

    //Add in the fetchFinancialNewsData() helper method that ties all the steps together
    // creating a URL, sending the request, processing the response.

    //Query the USGS dataSet and return a list of financialNews objects
    public static List<FinancialNews> fetchFinancialNewsDataString(String requestUrl) {

        // Let's create the url object
        URL url = createURL(requestUrl);

        //Let's perform the Http request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "There was a problem making the HTTP request.", e);
        }

        //Extract the relevant fields from the JSON response and create a list of financial News
        List<FinancialNews> financialNews = extractFeatureFromJson(jsonResponse);

        //Return the list of Financial News
        return financialNews;
    }
}// end class