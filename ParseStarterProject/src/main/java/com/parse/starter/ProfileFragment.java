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

public class ProfileFragment extends Fragment implements OnRequestSocialPersonCompleteListener
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
	@BindView(R.id.userInfoView)
	private TextView info;
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
		//set views references
		root = LayoutInflater.from(this).inflate(R.id.profile_fragment, container, false);
		BitterKnife.bind(root, this);


		showFavouriteCitations.setOnClickListener(showFavouriteClick);
		changeData.setOnClickListener(changeUserData);
		colorProfile(networkId);

		socialNetwork = LoginWithSocialMediaFragment.mSocialNetworkManager.getSocialNetwork(networkId);
		socialNetwork.setOnRequestCurrentPersonCompleteListener(this);
		socialNetwork.requestCurrentPerson()

		LoginWithSocialMedia.showProgress("loading account");
		return root;

	}

	//menu initialization
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inlate(R.menu.main, menu);
	}
	//menu actions
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item)
		{
				//add menu items here
			//	case R.id.action_logout:

		}
	}
	//create import to ParseUser later
	@Override
	public void onRequestSocialPersonSuccess(int i, SocialPerson socialPerson)
	{
			LoginWithSocialMedia.hideProgress();
			name.setText(socialPerson.name);
			id.settext(socialPerson.name);
			id.setText(SocialPerson.id);
			String socialPersonString = socialPerson.toString();
			String infoString = socialPersonString.substring(socialPersonString.indexOf("{")+1, socialPersonString.lastIndexOf("}"));
			info.setText(infoString.replace(", ", " /n"));
			Picasso.with(getActivity())
									.load(socialPerson.avatarURL)
									.into(photo);
	}

	private View.OnClickListener showFavouriteClick = new View.OnClickListener()
	{
		@Override
		public void onClick(View view)
		{
			//add reference to favourites activity/page/fragment

		}
	}
	private View.OnClickListener changeUserData = new View.OnClickListener()
	{
		@Override
		public void onClick(View view)
		{

		}
	}
	//change color of profile dependind on social network
	private void colorProfile(int networkId)
	{
			int color = getResources().getColor(R.color.dark);
			int image = R.drawable.user;
			switch(networkId)
			{
				case VkSocialNetwork.ID:
					color = getResources().getColor(R.color.vk);
					image = R.drawable.vk_user;
					break;
				case GooglePlusSocialNetwork.ID:
					color = getResources().getColor(R.color.google_plus);
					image = R.drawable.google_user;
					break;
			}
			frame.setBackgroundColor(color);
			name.setTextColor(color);
			profileImage.setBackgroundColor(color);
			profileImage.setImageResource(image);
	}
}
