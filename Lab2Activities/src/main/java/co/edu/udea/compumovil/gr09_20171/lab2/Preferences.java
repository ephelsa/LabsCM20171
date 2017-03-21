package co.edu.udea.compumovil.gr09_20171.lab2;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by leonardo on 21/03/17.
 */

public class Preferences extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
