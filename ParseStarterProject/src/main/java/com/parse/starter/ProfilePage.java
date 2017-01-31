package pakuteam.bedlam_experiment_0_1;

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
import android.widget.ListView;
import android.widget.ImageView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.app.LoaderManager.LoaderCallbacks;

public class ProfilePage extends AppCompatActivity
{
	private ImageView profileImage
	private TextView login;
	private ImageView showFavouriteCitations;
	private ImageView changeData;

	void onCreate(Bundle savedInstance)
	{
		ParseUser currentUser = ParseUser.getCurrent;
		super.OnCreate(savedInstance);
		setContentView(R.layout.ProfilePage);
		profileImage = (ImageView) findViewById(R.id.profImage);
		try
		{
			ParseQuery<ParseUser> u = currentUser.getQuery();
			Drawable image;
			u.whereEqualTo("UserImage", image.getType());
			query.findInBackground(new FindCallback<ParseUser>() 
			{
  				public void done(List<ParseUser> objects, ParseException e) 
  				{
    				if (e == null) 
    				{
    					Drawable dr = objects.stream()
    											.filter(e -> e instanceof Drawable);

        				profileImage.setImageDrawable(objects(1));
    				} 
    				else 
    				{
        				profileImage.setImageDrawable(R.id.dafault_user_image)
    				}
  				}

			});
		}
		catch(ParseException e)
		{
			profileImage.setError(R.strings.error_noImage);
		}
		
		login = (TextView) findViewById(R.id.usrLogin);
		showFavouriteCitations = (ImageView) findViewById(R.id.showfav);
		changeData = (ImageView) findViewById(R.id.change_login);
	}

	//load page with favourite citations
	showFavouriteCitations.setOnClickListener(OnClickListener c ->
	{
		ParseUser currentUser = ParseUser.getCurrent;
		if(currentUser != null)
		{		
			startActivity(new Intent(FavouriteCitations.This, FavouriteCitations.class));	
		}
	});
}