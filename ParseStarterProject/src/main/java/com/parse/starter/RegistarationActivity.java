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

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationActivity extends AppCompatActivity implements LoaderCallbacks<Cursor>
{
	private UserLoginTask mAuthTask = null;
	//UI references
	@BindView(R.id.email) private EditText EmailView;
	@BindView(R.id.password) private EditText PasswordView;
	@BindView(R.id.password_1) private EditText ConfirmPasswordView;
	@BindView(R.id.login) private EditText LoginView;
	@BindView(R.id.email_registration_button) private Button email_registration;
	@BindView(R.id.social_registration_button) private Button social_registration;
	private View mProgressView;
	private View RegistrationFormView;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		email_registration.setOnClickListener((View v) ->
		{
			attemptEmailRegistration();
		});

		social_registration.setOnClickListener((View v) ->)
		{
			attemptSocialRegistration();
		};
	}


	private void attemptEmailRegistration(String email, String password, String confirmPassword, String login)
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
		// check whether
		if(TextUtils.isEmpty(email) && !ProfileDataHelper.isEmailValid(email))
		{
			EmailView.setError(getString(R.strings.emailIsNotValidOrEmpty));
			focusView = EmailView;
			cancel = true;
		}

		if(TextUtils.isEmpty(username) && !ProfileDataHelper.isEmailValid(username))
		{
			LoginView.setError(getString(R.strings.usernameIsNotValidOrEmpty));
			focusView = LoginView;
			cancel = true;
		}

		if(TextUtils.isEmpty(password) && !ProfileDataHelper.isEmailValid(password))
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
		if(isLoginExist(username))
		{
			LoginView.setError(getString(R.strings.loginExistError));
			focusView = LoginView;
			cancel = true;
		}
		if(isEmailExist(email))
		{

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
			LoginWithSocialmedia.showProgress(R.strings.registration_in_progress);
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

    private boolean isLoginExist(String newLogin)
    {
    	ParseQuery<ParseUser> u = ParseUser.getQuery();
    	//Search object with username property

    	u.whereEqualTo("username", newLogin);
    	u.findInBackground(new FindCallBack<ParseUser>()
    	{
    		(List<ParseUser> objects, ParseException e) ->
    		{

					return e == null ? true:false;
    	});


    }

		private boolean isEmailExist(String newEmail)
		{

			ParseQuery<ParseUser> u = ParseUser.getQuery();
			//Search object with username property

			u.whereEqualTo("email", newEmail);
			u.findInBackground(new FindCallBack<ParseUser>()
			{
				(List<ParseUser> users, ParseException e) ->
				{

					return e == null ? true:false;
			});

		}

    @Override
    protected void onCancelled()
    {
    	mAuthTask = null;
    	LoginWithSocialMedia.hideProgress()
    }
}
