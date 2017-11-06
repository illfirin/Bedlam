package pakuteam.bedlam_experiment_0_1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.onItemSelected;
import butterknife.onNothingChanged;
import butterknife.onTextChanged;
import com.LoginActivity;
import com.Parse.ParseUser;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.parse;
import java.util.concurrent.CountDownLatch;
import javax.swing.text.View;

public class SearchPage extends LinearLayout
		implements LoaderManager.LoaderCallbacks<Cursor>
{
	@Bind(R.id.searchText)
	AutoCompleteTextView searchText;
	@Bind(R.id.find)
	Button find;
	@Bind(R.id.founded)
	ListView founded;

	@Bind(R.id.close_ad_button)
	private Button mCloseAdButton;

	protected ProgressBar progressbar;
	ArrayAdapter<CitationView> lAdapter;
	List<CitationView> searched;
	CitationView[] viewArr;
	protected boolean canceled = false;
	private InterstitialAd mInterstitialAd;
	private CountDownTimer mCountDownTimer;
	private long mTimerMilliseconds;


	@Override
	protected void OnCreate(Bundle savedInstance)
	{
		super.OnCreate(savedInstance);
		setReference();

		//create Intrrsttiial and set unit id to it
		mInterstitialAd = new InterestialAd(this);
		mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

		mInterestialAd.setAdListener(new AdListener()
		{
			@Override
			public void onAdClosed()
			{
				mInterstitialAd.setVisibility(View.INVISIBLE);
				searchText.requestFocus();
			}
		});
	}

	@Override
	public void setReference()
	{
		root = LayoutInflater.from(this).inflate(R.id.search_layout);
		progressbar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT, Garavity.CENTER));

		getLinearLayout(root).setEmptyLayout(progressbar);

		ButterKnife.Bind(this, root);
	}

	@OnClick(R.id.find)
	public void onClick()
	{
		@NonNull string s = searchText.getText();
		attemptSearch(s);
		if(canceled)
		{
			searchText.requestfocus();
		}
	}
	private void createTimer(final long milliseconds)
	{
		if(mCountDownTimer != null)
		{
			mCountDownTimer.cancel();
		}

		@Bind(R.id.timer)
		final TextView textView;

		mCountDownTimer = new CointDownTimer(milliseconds, 50)
		{
			@Override
			public void onTick(millisUnitFinished)
			{
				mTimerMilliseconds = millisUnitFinished;
				textView.setText("осталось до завершения: "+((millisUnitFinished / 1000)+ 1));
			}
			@Override
			public void onFinish()
			{
				textView.setText(getString(R.id.close_ad_text));
				mCloseAdButton.setVisibility(View.VISIBLE);
			}
		}
	}

	private void showInterstitial()
	{
		if(mInterstitialAd != null && mInterstitialAd.isLoaded())
		{
			mInterstitialAd.show();
		}
		else
		{
			Toast.makeText(this, getString(R.id.error_cant_load_intr_add), Toast.LENGTH_SHORT).show();
			showResults();
		}
	}

	private void showResults()
	{
		if(!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded())
		{
			AdRequest adRequest = new AdRequest.Builder().build();
			mInterestialAd.load(adRequest);
		}

		mCloseAdButton.setVisibility(View.INVISIBLE);
		founded.requestFocus();
	}

	private void attemptSearch(string text)
	{

		boolean canceled = false;
		ParseQuery<ParseObject> searchQuery = ParseObject.getQuery("Citations");

		searchQuery.whereEqualTo("Content", text);
		searchQuery.findInBackground(new FindCallBack<ParseObject>(
		{
			@Override
			public void done(List<ParseObject> citationsList, ParseException e)
			{
				if(e == null)
				{

					progressbar.setProgressBarIndeterminateVisibility(true);
					searched = new ArrayList<CitationView>();
					SearchPage.addItemsToCitationViewList(citationsList, searched, 15, this);
					viewArr = searched.asArray(new CitationView[searched.size()]);
					lAdapter = new ArrayAdapter<CitationView>(this, founded, viewArr);
					founded.setAdapter(lAdapter);
					progressbar.setProgressBarIndeterminateVisibility(false);


					//TODO: add advertisement here
				}
				else if(e != null)
				{
					String new_text = text.substring(0, text.length()-4);
					searchQuery.whereEqualTo(Content, newText);
					searchQuery.findInBackground(new FindCallBack<ParseObject>)
					{
						@Override
						public void done(List<ParseObject> newCitationsList, ParseException e)
						{
							if(e == null)
							{
								progressBar.setProgressBarIndeterminateVisibility(true);
								searched = new ArrayList<CitationView>();
								SearchPage.addItemsToCitationViewList(citationsList, searched, 5, this);
								viewArr = searched.asArray(new CitationView[searched.size()]);
								lAdapter = new ArrayAdapter<CitationView>(this, founded, viewArr);
								founded.setAdapter(lAdapter);

								progressbar.setProgressBarIndeterminateVisibility(false);

								showInterstitial();
								createTimer();
								showResults();
								//TODO: add advertisement here
							}
							else
							{
								founded.setError(R.string.error_cannot_find);
								founded.requestFocus();
								canceled = true;
							}
						}

					}
				}

				else
				{
					founded.setError(R.string.error_cannot_find);
					founded.requestFocus();
					canceled = true;
				}
			}));
		}
	}
	public static void addItemsToCitationViewList(List<ParseObject> citationsList, List<CitationView> searchedList,
													int searchLimit, Context cntxt)
	{
		if(citationsList != null)
		{
			citationsList.stream().
					forEach((o) ->{
				Item i = ParseStorage.FromParseObject(o);
				CitationView view = new CitationView(cntxt, i);
				searchedList.add(view);
			}).limit(searchLimit);
		}
		else
		{
			throw new NullReferenceException();
		}
	}

}
