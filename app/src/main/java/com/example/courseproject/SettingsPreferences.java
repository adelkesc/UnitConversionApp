package com.example.courseproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingsPreferences extends PreferenceFragmentCompat {

    SharedPreferences shared;
    SharedPreferences.Editor editor;

    CheckBoxPreference checkBoxPreference;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        checkBoxPreference = (CheckBoxPreference) getPreferenceManager()
                .findPreference("color_choice_dark");

        checkBoxPreference.setOnPreferenceChangeListener(new Preference.
                OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                return false;
            }
        });
        return null;
    }

}
