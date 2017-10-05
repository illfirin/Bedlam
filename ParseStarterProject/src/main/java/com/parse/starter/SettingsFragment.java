package pakuteam.bedlam_experiment_0_1;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.SwitchPreference;
import android.support.v7.preference.SharedPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.annotation.NonNull;

public class SettingsFragment extends PreferenceFragmentCompat
{
 	protected ListPreference lp;
	protected SwitchPreference sp;

	@Override
	public void OnCreatePreferences(Bundle savedInstance, String s)
	{
		 	super.onCreate(savedInstance);
			addPreferencesFromResource(R.xml.settings);
	}

	@Override
	public void onResume()
	{
			super.onResume();

			getPreferenceManager().getSharedPreference().registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onPause()
	{
			super.onPause();

			getPreferenceManager().getSharedPreference().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
	{
			String currValue = null;
			lp = (ListPreference) findPreference("choose_theme");
			sp = (SwitchPreference) findPreference("allow_sync");
			if(@NonNull lp)
			{
					CharSequence currText = lp.getEntry();
					currValue = lp.getValue();
			}

			if(@NonNull currValue)
			{

			}

	}

}
