package pakuteam.bedlam_experiment_0_1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.Parse.ParseUser;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;
import java.util.regexp;
import java.util.ArrayList;
import java.util.List;
import java.com.io;

public class RegistrationActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>
{
	private UserLoginTask mAuthTask = null;
	//UI references
	private EditText EmailView;
	private EditText PasswordView;
	private EditText ConfirmPasswordView;
	private EditText LoginView;
	private View mProgressView;
	private View RegistrationFormView;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		EmailView = (EditText) findViewById(R.id.email);
		PasswordView = (EditText) findViewById(R.id.password);
		ConfirmPasswordView = (EditText) findViewById(R.id.password_1);
		LoginView = (EditText) findViewById(R.id.login);

		Button EmailRegistrationButton = (Button) findViewById(R.id.email_registration);
		EmailRegistrationButton.setOnClickListener((View v)->
		{
			attemptRegistration();
		});
	}


	private void attemptRegistration()
	{
		if(mAuthTask != null)
		{
			return;
		}

		//Reset Errors
		EmailView.setError(null);
		PasswordView.setError(null);
		ConfirmPasswordView.setError(null);
		LoginView.setError(null);
		RegistarationFormView.setError(null);

		//Store values
		String email = EmailView.getText().ToString();
		String password = PasswordView.getText().ToString();
		String login = LoginView.getText().ToString();
		String confirmPassword = ConfirmPasswordView.getText().ToString();
		boolean cancel = false;
		View focusView = null;

		if(TextUtils.isEmpty(email) && !isEmailValid(email))
		{
			EmailView.setError(getString(R.strings.emailIsNotValidOrEmpty));
			focusView = EmailView;
			cancel = true;
		}

		if(TextUtils.isEmpty(username) && !isEmailValid(username))
		{
			LoginView.setError(getString(R.strings.usernameIsNotValidOrEmpty));
			focusView = LoginView;
			cancel = true;
		}

		if(TextUtils.isEmpty(password) && !isEmailValid(password))
		{
			EmailView.setError(getString(R.strings.passwordIsNotValidOrEmpty));
			focusView = PasswordView;
			cancel = true;
		}

		if(!password.Equals(confirmPassword))
		{
			EmailView.setError(getString(R.strings.passwordIsNotEquals));
			focusView = EmailView;
			cancel = true;
		}
		if(cancel)
		{
			//error, focus field with an error
			focusView.requestFocus();
		}
		else
		{
			//show progress spinner
			//perform registration
			showProgress(true);
			public static void PRegistration(string email, string password, string confirmPassword, string login)
			{

				ParseUser user = new ParseUser();
				user.setUsername(email);
				user.setPassword(password);
				user.setEmail(email);

				user.SignUpInBackGround(new SignUpCallBack()
				{
					public void done(ParseUser user, ParseException ex)
					{
						if (e == null)
						{
							startActivity(new Intent(MainActivity.This, MainActivity.class));

						}
						else
						{
							focusView = RegistrationFormView;
							RegistrationFormView.setError(ex.ToString());
						}
					}
				});
			}
		}
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            RegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            m.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter()
				{
					@Override
					public void onAnimationEnd(Animator animation)
					{
						mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
					}
				});

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            RegistrationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

	private boolean isEmailValid(String email)
	{
        //Check wether it is e-mail or something else
		Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$", Pattern.CASE_INSENSITIVE);
		Matcher m = pattern.matcher(email)
        return m.matches;
    }

	private boolean isPasswordValid(String password)
	{
		List<Character> pass = password.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
		boolean HasUpper = pass.stream().HasAny(e -> Character.isUpperCase(e) )
        //Check for length and containing of UpperCase characrets
        return password.length() > 6 && HasUpper;
    }

    private boolean isLoginExist(String newLogin)
    {
    	ParseQuery<ParseUser> u = ParseUser.getQuery();
    	//Search object with username property

    	u.whereEqualTo("username", newLogin);
    	u.findInBackground(new FindCallBack<ParseUser>()
    	{
    		(List<ParseUser> objects, ParseException e) ->
    		{
    			if(e == null)
    			{
    				return;
    			}
    			else
    			{
    				LoginView.setError(getString(R.string.error_usernameAlreadyExist));
    				LoginView.requestFocus();
    			}
    		}
    	});


    }

    @Override
    protected void onCancelled()
    {
    	mAuthTask = null;
    	showProgress(false);
    }
}
