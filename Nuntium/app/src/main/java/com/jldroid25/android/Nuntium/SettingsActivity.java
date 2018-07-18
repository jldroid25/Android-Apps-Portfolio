package com.jldroid25.android.Nuntium;

import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

    } //end onCreate

    // referenceFragment inner Class
    public static class FinancialNewsPreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            //update the preference summary (the UI) when the settings activity is launched in onCreate().

            //using findPreference() To help us with bind the value in SharedPreferences
            // to what will show up in the preference summary.
            Preference pageSize = findPreference(getString(R.string.settings_min_articles_key));
            bindPreferenceSummaryToValue(pageSize);

            //Logic for the order_by Preference
            Preference orderBy = findPreference(getString(R.string.settings_order_by_key));
            bindPreferenceSummaryToValue(orderBy);

            //Logic for the specific production office(UK or USA) Preference
            Preference prodOffice = findPreference(getString(R.string.settings_production_office_key));
            bindPreferenceSummaryToValue(prodOffice);
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            preference.setOnPreferenceChangeListener(this);
            SharedPreferences preferences = PreferenceManager
                    .getDefaultSharedPreferences(preference.getContext());
            String preferenceString = preferences.getString(preference.getKey(), "");
            onPreferenceChange(preference, preferenceString);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {

            //This method takes care of updating the displayed preference summary
            // after it has been changed
            String stringValue = value.toString();

            //Properly update the summary of a ListPreference(using the label, instead of the key):
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;

                int prefIndex = listPreference.findIndexOfValue(stringValue);

                if (prefIndex >= 0) {
                    CharSequence[] labels = listPreference.getEntries();
                    preference.setSummary(labels[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
            return true;
        }
    }
}