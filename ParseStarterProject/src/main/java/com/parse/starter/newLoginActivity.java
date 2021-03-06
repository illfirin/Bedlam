package pakuteam.bedlam_experiment_0_1; ;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.IntentSender;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utils.Constants;
import utils.ValidateUserInfo;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.FacebooUtils
import static android.Manifest.permission.READ_CONTACTS;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * A login screen that offers login via email/password.
 */
public class newLoginActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    /**
    @BindView(R.id.facebook_button) private Button mFacebookLoginButton;
    @BindView(R.id.google_button)   private Button googleLoginButton;
    @BindView(R.id.custom_signIn)   private Button customSigninButton;
    @BindView(R.id.custom_signUp)   private Button customSignupButton;
    @BindView(R.id.logout_button)   private Button logoutButton;
    @BindView(R.id.email_textholder) private EditText emailEditText;
    @BindView(R.id.password_textholder) private EditText passwordEditText;

    txt_create = (TextView) findViewById(R.id.txt_create);
    txt_forgot = (TextView) findViewById(R.id.txt_forgot);
    mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    mPlusSignInButton = (SignInButton) findViewById(R.id.g_sign_in_button);
    mFacebookLoginButton = (LoginButton)findViewById(R.id.f_sign_in_button);

     * Keep track of the login task to ensure we can cancel it if requested.
     */

    private UserLoginTask mAuthTask = null;

    // UI references.
    @BindView(R.id.) private AutoCompleteTextView mEmailView;
    @BindView(R.id.) private EditText mPasswordView;
    @BindView(R.id.) private ProgressDialog pd;
    @BindView(R.id.) private View mLoginFormView;

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;
    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    private CallbackManager callbackManager;
/*
    txt_create = (TextView) findViewById(R.id.txt_create);
    txt_forgot = (TextView) findViewById(R.id.txt_forgot);
    mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
    mPlusSignInButton = (SignInButton) findViewById(R.id.g_sign_in_button);
    mFacebookLoginButton = (LoginButton)findViewById(R.id.f_sign_in_button); */
    @BindView(R.id.g_sign_in_button) private SignInButton mPlusSignInButton;
    @BindView(R.id.email_sign_in_button) private Button mEmailSignInButton;
    @BindView(R.id.tw_sign_in_button) private Button mTwitterSignInButton;
    @BindView(R.id.f_sign_in_button) private Button mFacebookSignInButton;

    @BindView(R.id.txt_create) private TextView txt_create;
    @BindView(R.id.txt_forgot) private TextView txt_forgot;

    protected ParseUser user;
    ProgressDialog pd;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
      super.onActivityResult(requestCode, resultCode, data);
      ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initInstances();
    }

    private void initInstances()
    {
        // Set up the login form.

        populateAutoComplete();

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if (id == R.id.login || id == EditorInfo.IME_NULL)
                {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        txt_create.setOnClickListener(this);
        txt_forgot.setOnClickListener(this);

        mEmailSignInButton.setOnClickListener(this);

        //Google+ Login
        mPlusSignInButton.setSize(SignInButton.SIZE_WIDE);
        mPlusSignInButton.setOnClickListener(this);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        mTwitterSignInButton.setOnClickListener((View v) ->
        {
          LoginWithSocialMediaHelper.showProgress(pd);
          ParseTwitterUtils.logIn(this, permissions, new LogInCallback()
          {
              @Override
              public void done(Parse user, ParseException e)
              {
                if(user == null)
                {
                    Sentry.capture(e);
                    e.printStackTrace();
                    LoginWithSocialMediaHelper.hideProgress(pd);
                }
                else if(user.isNew())
                {
                    Log.d("Bedlam", "User auth using twitter");
                    LoginWithSocialMediaHelper.hideProgress(pd);
                }
                else
                {
                    LoginWithSocialMediaHelper.hideProgress(pd);
                    startActivity(new Intent(MainActivity.This, MainActivity.class));
                }
              }
          });
        });
        // Callback registration
        mFacebookLoginButton.setOnClickListener((View v) ->
        {
              LoginWithSocialMediaHelper.showProgress(pd);
              ParseFacebookUtils.logInWithReadPermissionsInBackground(this, permissions, new LogInCallback()
              {
                  @Override
                  public void done(Parse user, ParseException e)
                  {
                    if(user == null)
                    {
                        Sentry.capture(e);
                        e.printStackTrace();
                        LoginWithSocialMediaHelper.hideProgress(pd);
                    }
                    else if(user.isNew())
                    {
                        Log.d("Bedlam", "User auth using facebook");
                        LoginWithSocialMediaHelper.hideProgress(pd);
                    }
                    else
                    {
                        LoginWithSocialMediaHelper.hideProgress(pd);
                        startActivity(new Intent(MainActivity.This, MainActivity.class));
                    }
                  }
              });
        });

        twit



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin()
    {
        if (mAuthTask != null)
        {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!LoginHelper.isLoginInformationCorrect(email, password))
        {
            mPasswordView.setError(getString(R.string.error_invalid_loginInformation));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else
        {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            LoginWithSocialMediaHelper.showProgress(pd);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection)
     {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        String email = mEmailView.getText().toString();

        switch (v.getId())
        {
            case R.id.g_sign_in_button:
                onSignInClicked();
                break;
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
            case R.id.txt_create:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra(Constants.TAG_EMAIL, email);
                startActivity(intent);
                finish();
                break;
            case R.id.txt_forgot:
                Intent intentForgot = new Intent(LoginActivity.this, ForgotPassActivity.class);
                intentForgot.putExtra(Constants.TAG_EMAIL, email);
                startActivity(intentForgot);
                finish();
                break;
        }
    }

    private void onSignInClicked()
    {
//        toastLoading.show();
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        //showProgress(String message, Context context, ProgressDialog pd)

        LoginWithSocialMedia.showProgress("Connecting...", this, pd)
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    mShouldResolve = true;
                    mGoogleApiClient.connect();
                }
                catch (Exception e)
                {
                    LoginWithSocialMediaHelper.hideProgress(pd);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        mShouldResolve = false;
        getProfileInformation();
    }

    @Override
    public void onConnectionSuspended(int arg0)
    {
        LoginWithSocialMediaHelper.hideProgress(pd);
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d(Constants.TAG_LOGIN, "onConnectionFailed:" + connectionResult);
        LoginWithSocialMediaHelper.hideProgress(pd);

        if (!mIsResolving && mShouldResolve)
        {
            if (connectionResult.hasResolution())
            {
                try
                {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                }
                catch (IntentSender.SendIntentException e)
                {
                    Log.e(Constants.TAG_LOGIN, "Could not resolve ConnectionResult.", e);
                    Toast.makeText(LoginActivity.this, "Could not resolve ConnectionResult", Toast.LENGTH_LONG).show();
                    mIsResolving = false;
                }
            }
            else
            {
                // Could not resolve the connection result, show the user an
                // error dialog.
                Toast.makeText(LoginActivity.this, "Error on Login, check your google + login method", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            // Show the signed-out UI
        }
    }

    /**
     * Fetching user's information name, email, profile pic
     * */
    private void getProfileInformation()
    {
        LoginWithSocialMediaHelper.hideProgress(pd);
        if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
            Person currentPerson = Plus.PeopleApi
                    .getCurrentPerson(mGoogleApiClient);
            String personName = currentPerson.getDisplayName();
            String personPhotoUrl = currentPerson.getImage().getUrl();
            String personGooglePlusProfile = currentPerson.getUrl();
            String birth = currentPerson.getBirthday();
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

            // by default the profile url gives 50x50 px image only
            // we can replace the value with whatever dimension we want by
            // replacing sz=X
//                personPhotoUrl = personPhotoUrl.substring(0,
//                        personPhotoUrl.length() - 2)
//                        + PROFILE_PIC_SIZE;

            //new LoadProfileImage().execute(personPhotoUrl);

        } else {
            Toast.makeText(getApplicationContext(),
                    "Person information is null", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params)
         {
            try
            {
                // Simulate network access.
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                return false;
            }
            //login if user's information is valid
            if(LoginHelper.isLoginInformationCorrect(mEmail, mPassword))
            {
    			      ParseUser.logInBackGround(mEmail, mPassword, new LogInCallback(
    				    {
    					         public void done(ParseUser user, ParseException e)
    					         if(user != null)
    					         {
    						             startActivity(new Intent(MainActivity.This, MainActivity.class));
    					         }

    					         else
    					         {
                              Sentry.capture(e);
    						              e.Message.Show();
    					         }
    				    });

            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            mAuthTask = null;
            LoginWithSocialMediaHelper.hideProgress(pd);

            if (success)
            {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else
            {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled()
        {
            mAuthTask = null;
            LoginWithSocialMediaHelper.hideProgress(pd);
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        if (mGoogleApiClient.isConnected())
        {
            mGoogleApiClient.disconnect();
        }
    }
}
