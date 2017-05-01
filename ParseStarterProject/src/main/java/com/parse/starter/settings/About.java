import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.LoginActivity;
import com.parse;
import com.Parse.ParseUser;
import android.os.Build;
import android.os.Bundle;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Spinner;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

public class About extends LinearLayout
{
    protected ImageView logo;
    protected TextView information;
    protected TextView company_mail;

    @Override
    public void OnCreate(Bundle savedInstance)
    {
        super.OnCreate(savedInstance);
        ProgressBar progressbar = new ProgressBar(this);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                      LayoutParams.WRAP_CONTENT, Garavity.CENTER));
        progressbar.setProgressBarIndeterminateVisibility(true);
        logo = (ImageView) findViewById(R.id.logo);
        String uri = "@drawable/company_logo";
        int imResource = getResources().getIdentifier(uri, null, getPackageName);
        Drawable res = getResources().getDrawable(inResource);
        logo.setImageDrawable(res);
        information = (TextView) findViewById(R.id.information);
        information.setTextViewText(getText(R.strings.information));

        company_mail = (TextView) findViewById(R.id.company_mail)
        company_mail.setTextViewText(getText(R.strings.company_mail));
        progressbar.setProgressBarIndeterminateVisibility(false);
    }
}
