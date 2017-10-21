package pakuteam.bedlam_experiment_0_1;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import android.support.annotation.NonNull;
import com.parse.starter.MainController;
import com.parse.starter.R;
import com.parse.starter.UserPreferenceManager;
import com.anupcowkur.reservoir.Reservoir;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public abstract class BaseActivity extends AppCompatActivity
{
    private View view_simpleToolbar;
    public FrameLayout container;
    public android.support.v7.widget.Toolbar toolbar;
    public CoordinatorLayout mainLayout;
    public AppBarLayout base_toolbarContainer;

   private static String Theme_Current = "AppliedTheme";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        try
        {
            setAppTheme();
            super.onCreate();
            setContentView(R.layout.activity_base);

            base_toolbarContainer = (AppBarLayout) findViewById(R.id.base_appbar);
            container = (FrameLayout)findViewById(R.id.container);
            mainLayout = (CoordinatorLayout) findViewById(R.id.full_layout);
            mAdView = (AdView) findViewById(R.id.View);
            AdRequest adReq = new AdRequest.Builder().build();
            mAdView.loadAd(adReq);
            //TODO: enter admob app id
            MobileAds.initialize(this, "here");

            Reservoir.init(this, 8192);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setSimpleToolbar(boolean isSimpleToolbarRequire)
    {
        if(isSimpleToolbarRequire)
        {
            view_simpleToolbar = LayoutInflater.from(this).inflate(R.layout.simple_toolbar, base_toolbarContainer)
            toolbar = (android.support.v7.widget.Toolbar) view_simpleToolbar.findViewById(R.id.toolbar);
            if(/*@NonNull*/ toolbar)
            {
                setSupportActionBar(toolbar);
                toolbar.setTitle(R.string.application_name);
                toolbar.setTitleTextColor(Color.WHITE);
            }
        }
    }

    public void setToolbarSubTitle(String header)
    {
        if(/*@NonNull*/ toolbar)
        {
            toolbar.setSubtitle(header);
        }
    }

    public void setToolbarElevation(float value)
    {
        if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            if(/*@NonNull*/ toolbar)
            {
                toolbar.setElevation(value);
            }
        }
    }
    //set XML objext reference
    public abstract void setReference();

    private void setAppTheme()
    {
        //Will be added later
        if(!UserPreferenceManager.preferenceGetString(Theme_Current, "").equals(""))
        {
            if(UserPreferenceManager.preferenceGetString(Theme_Current, "").equals("Green"))
            {
                setTheme(R.style.ThemeApp_Green)
            }
            else if(UserPreferenceManager.preferenceGetString(Theme_Current, "").equals("Purple"))
            {

            }
            else
            {
                    setTheme(R.style.ThemeApp_Green);
            }
            //etc for all of themes
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        try
        {
            Reservoir.clear();
        }
        catch(Exception e)
        {

        }
    }
}
