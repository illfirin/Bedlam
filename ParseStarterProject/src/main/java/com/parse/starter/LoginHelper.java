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


import java.util.ArrayList;
import java.util.List;
import java.com.io;
//bugtracking
import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginHelper implements ILogin
 {

    public static boolean isLoginInformationCorrect(String email, String password)
    {
      if (TextUtils.isEmpty(password) || !ProfileDataHelper.isPasswordValid(password) || password==null
            || (TextUtils.isEmpty(email) || !ProfileDataHelper.isEmailValid(password) || email==null )
       {
         return false;
       }
       else
       {
         return true;
       }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin(String email, String password)
    {
        if(isLoginInformationCorrect(email, password))
            LoginWithSocialMedia.showProgress();
			      ParseUser.logInBackGround(mail, password, new LogInCallback(
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

}
