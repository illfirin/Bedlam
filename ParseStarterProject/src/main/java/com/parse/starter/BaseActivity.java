package pakuteam.bedlam_experiment_0_1;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;


import com.parse.Parse;

import butterKnife.ButterKnife;
import butterKnife.BindView;

import android.support.annotation.NonNull;
import com.parse.starter.MainController;
import com.parse.starter.R;
import com.parse.starter.UserPreferenceManager;
import com.anupcowkur.reservoir.Reservoir;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.android.AndroidSentryClientFactory;

public class BaseApplication extends Application
{
    private View view_simpleToolbar;
    public FrameLayout container;
    //View bindings
    @BindView(R.id.facebook_button) private Button facebookLoginButton;
    @BindView(R.id.google_button)   private Button googleLoginButton;
    @BindView(R.id.custom_signIn)   private Button customSigninButton;
    @BindView(R.id.custom_signUp)   private Button customSignupButton;
    @BindView(R.id.logout_button)   private Button logoutButton;
    @BindView(R.id.facebook_button) private EditText emailEditText;
    @BindView(R.id.facebook_button) private EditText passwordEditText;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Context ctx = this.getApplicationContext();
        setContentView(R.layout.activity_login);
        //TODO: add app id later
        //Parse server initialization
        ButterKnife.bind(this);


            //TODO: add app id later
            //Parse server initialization
            Parse.initialize(new Parse.Configuration.Builder(this)
                            .applicationId("enter_app_id_here")
                            .server("https://myBedlamServer:8096/parse")
                            .build());
            ParseFacebookUtils.initialize(ctx);
            //Sentry initialization

            //use client key from project settings page
            String sentryDsn = "https://publicKey:secretKey@host:port/1?options";
            Sentry.init(sentryDsn, new AndroidSentryClientFactory(ctx));
            Sentry.init(new AndroidSentryClientFactory(ctx));


            setAppTheme();
            super.onCreate();
            setContentView(R.layout.activity_base);

            base_toolbarContainer = (AppBarLayout) findViewById(R.id.base_appbar);
            container = (FrameLayout)findViewById(R.id.container);
            mainLayout = (CoordinatorLayout) findViewById(R.id.full_layout);
            //Ads initialization
            mAdView = (AdView) findViewById(R.id.View);
            AdRequest adReq = new AdRequest.Builder().build();
            mAdView.loadAd(adReq);
            //TODO: enter admob app id
            MobileAds.initialize(this, "here");

            Reservoir.init(this, 8192);
        }
        catch(Exception e)
        {
            Sentry.capture(e);
            e.printStackTrace();
        }
    }
    /*@BindView(R.id.facebook_button) private Button facebookLoginButton;
    @BindView(R.id.google_button)   private Button googleLoginButton;
    @BindView(R.id.custom_signIn)   private Button customSigninButton;
    @BindView(R.id.custom_signUp)   private Button customSignupButton;
    @BindView(R.id.logout_button)   private Button logoutButton;*/
    private void setButtonListeners()
    {
      facebookLoginButton.setOnClickListener((View v) ->
      {
          smartLogin = SmartLoginFactory.build(LoginType.Facebook);
          smartLogin.login(config);
      });

      googleLoginButton.setOnClickListener((View v) ->
      {
        smartLogin = SmartLoginFactory.build(LoginType.Google);
        smartLogin.login(config);
      });

      customSignupButton.setOnClickListener((View v) ->
      {
        smartLogin = SmartLoginFactory.build(LoginType.Google);
        smartLogin.signup(config);
      });

      customSigninButton.setOnClickListener((View v) ->
      {
        smartLogin = SmartLoginFactory.build(LoginType.Custom);
        smartLogin.login(config);
      });

      logoutButton.setOnClickListener((View v) ->
      {
        if (currentUser != null)
        {
          if (currentUser instanceof SmartFacebookUser)
          {
              smartLogin = SmartLoginFactory.build(LoginType.Facebook);
          }
          else if(currentUser instanceof SmartGoogleUser)
          {
              smartLogin = SmartLoginFactory.build(LoginType.Google);
          }
          else
          {
              smartLogin = SmartLoginFactory.build(LoginType.CustomLogin);
          }
          boolean result = smartLogin.logout(BaseActivity.this);
          if (result)
          {
              refreshLayout();
              Toast.makeText(MainActivity.this, "User logged out successfully", Toast.LENGTH_SHORT).show();
          }
      });
    }
  }
}
