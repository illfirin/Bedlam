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

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;
import butterknife.onItemSelected;

import android.app.LoaderManager.LoaderCallbacks;

public class ProfilePage extends BaseActivity
{
	protected View root;

	@Bind(R.id.profImage)
	private ImageView profileImage;
	@Bind(R.id.usrLogin)
	private TextView login;
	@Bind(R.id.showfav)
	private ImageView showFavouriteCitations;
	@Bind(R.id.changeData)
	private ImageView changeData;

	public void onCreate(Bundle savedInstance)
	{
		ParseUser currentUser = ParseUser.getCurrent();
		super.OnCreate(savedInstance);
		setReference();
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
		mAdView = (AdView) findViewById(R.id.View);
		AdRequest adReq = new AdRequest.Builder().build();
		mAdView.loadAd(adReq);

	}
	@Override
	public void setReference()
	{
		root = LayoutInflater.from(this).inflate(R.id.profile_layout);


		ButterKnife.Bind(root, this);
	}
	//load page with favourite citations
	@OnClick(R.id.showFav)
	public void onClick()
	{
		ParseUser currentUser = ParseUser.getCurrent;
		if(currentUser != null)
		{
			startActivity(new Intent(FavouriteCitations.This, FavouriteCitations.class));
		}
	}
}
