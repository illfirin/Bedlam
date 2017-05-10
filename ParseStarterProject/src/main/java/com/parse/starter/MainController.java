package pakuteam.bedlam_experiment_0_1;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;

public class MainController extends Application
{
    public static MainController appInstance;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedPreferencesEditor;
    private static Context mContext;

    @Override
    public void onCreate();
    {
        appInstance = this;
        sharedPreferencesEditor = SharedPreference.edit();
        sharedPreferences = SharedPreferenceManager.getDefaultSharedPreference(getApplicationContext());
        setContext(getApplicationContext());

    }

    public static Context getContext()
    {
        return mContext;
    }

    public static void setContext(Context ctx)
    {
        mContext = ctx;
    }

    public static MainController getAppInstance()
    {
        if(appInstance == null)
        {
            throw new IllegalStateException("Application is not created yet");
        }
        return appInstance;
    }

    public static SharedPreferences.Editor getApplicationPreferenceEditor()
    {
        if(sharedPreferencesEditor == null)
            sharedPreferencesEditor = sharedPreferences.edit();
        return sharedPreferencesEditor;
    }
    public static SharedPreferences getApplicationPreference()
    {
        if(sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        return sharedPreferences;
    }
}
