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
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.onItemSelected;

import com.github.gorbin.asne.core.SocialNetwork;
import com.github.gorbin.asne.core.listener.OnPostingCompleteListener;
import com.github.gorbin.asne.core.listener.OnRequestSocialPersonCompleteListener;
import com.github.gorbin.asne.core.persons.SocialPerson;
import com.github.gorbin.asne.facebook.FacebookSocialNetwork;
import com.github.gorbin.asne.googleplus.GooglePlusSocialNetwork;
import com.github.gorbin.asne.linkedin.LinkedInSocialNetwork;
import com.github.gorbin.asne.twitter.TwitterSocialNetwork;
import com.squareup.picasso.Picasso;

import android.app.LoaderManager.LoaderCallbacks;

public class ProfileFrame extends Fragment implements OnRequestSocialPersonCompleteListener
{
	protected View root;
	private static final String NETWORK_ID = "NETWORK_ID";
	private SocialNetwork socialNetwork;

	@BindView(R.id.profImage)
	private ImageView profileImage;
	@BindView(R.id.usrLogin)
	private TextView login;
	@BindView(R.id.showfav)
	private Button showFavouriteCitations;
	@BindView(R.id.changeData)
	private Button changeData;
	@BindView(R.id.profileFrame)
	private RelativeLayout frame;

	public static ProfileFragment newInstance(int id)
	{
		ProfileFragment fragment = new ProfileFragment();
		Bundle args = new Bundle();
		args.putInt(NETWORK_ID, id);
		fragment.setArguments(args);
		return fragment;
	}

	public ProfileFragment()
	{

	}

	@Override
	public void onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance)
	{
		//TODO:переделать
		/*
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
        				profileImage.setImageDrawable(R.id.dafault_user_image);
    				}
  				}

			});
		}
		catch(ParseException e)
		{
			profileImage.setError(R.strings.error_noImage);
		}

		//load page with favourite citations
		@OnClick(R.id.showFav)
		public void onClick()
		{
			ParseUser currentUser = ParseUser.getCurrent();
			if(currentUser != null)
			{
				startActivity(new Intent(FavouriteCitations.This, FavouriteCitations.class));
			}
		}

		mAdView = (AdView) findViewById(R.id.View);
		AdRequest adReq = new AdRequest.Builder().build();
		mAdView.loadAd(adReq);
		*/
		setHasOptionsMenu(true);
		networkId = getArguments().containsKey(NETWORK_ID) ? getArguments().getInt(NETWORK_ID) : 0;
		((LoginWithSocialMedia)getActivity()).getSupportActionBar().setTitle("Profile");

		setReference();
		showFavouriteCitations.setOnClickListener(showFavouriteClick);
	}

	@Override
	public void setReference()
	{
		rootView = LayoutInflater.from(this).inflate(R.id.profile_fragment);
		ButterKnife.bind(root, this);
	}

	private View.OnClickListener showFavouriteClick = new View.OnClickListener()
	{
		@Override
		public void onClick(View view)
		{
			
		}
	}

}
