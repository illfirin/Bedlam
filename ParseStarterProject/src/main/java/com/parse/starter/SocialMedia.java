package pakuteam.bedlam_experiment_0_1;


import android.net.Uri;
import android.os.AsyncTask;
import com.parse;
import com.Parse.ParseUser;
import com.parse.starter.LoginActivity;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;

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

public class SocialMedia extends LinearLayout
{
	private ImageView vk;
	private ImageView gplus;
	private ImageView faceB;
	private ImageView twitter;

	@Override
	protected void OnCreate(Bundle savedInstance)
	{
		vk = (ImageView)findViewById(R.id.vk_view);
		gplus = (ImageView)findViewById(R.id.gpls_view);
		faсB = (ImageView)findViewById(R.id.faceB_view);
		twitter = (ImageView)findViewById(R.id.twitter_view);

		vk.setOnClickListener(OnClickListener c ->
		{
			LoginActivity.showProgress(true);
			startActivity(new Intent(VkLogin.This, VkLogin.class))

		});
		gplus.setOnClickListener(OnClickListener c ->
		{
			LoginActivity.showProgress(true);
			startActivity(new Intent(GpLogin.This, GpLogin.class))

		});
		facB.setOnClickListener(OnClickListener c ->
		{
			LoginActivity.showProgress(true);
			startActivity(new Intent(facBLogin.This, facBLogin.class))

		});
		twitter.setOnClickListener(OnClickListener c ->
		{
			LoginActivity.showProgress(true);
			startActivity(new Intent(TwitterLogin.This, TwitterLogin.class))
		});

	}

}