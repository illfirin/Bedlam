package pakuteam.bedlam_experiment_0_1;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.parse.CitationView;

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
import android.widget.ImageView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import java.utils.stream;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class FavouriteCitations extends BaseActivity
{
	private ViewGroup root;
	ProgressBar progressbar;

	@Bind(android.R.layout.Favourite_citations)
	protected ListView lView;
	private ArrayAdapter<CitationView> mAdapter;
	protected CitationView[] fav_citations_views = FavouriteCitations.getCurrUserFavouriteCitationsViews();

 	private AdView adView;

	@Override
	protected void OnCreate(Bundle savedInstance)
	{
			super.OnCreate(savedInstance);

			adView = new AdView(this);
	        adView.size(AdSize.BANNER);
	        adView.setAdUnitId(R.string.constructor_ad_unitId);

			adView = (AdView) findViewById(R.id.View);
			AdRequest adReq = new AdRequest.Builder().build();
			adView.loadAd(adReq);

			progressbar.setIndeterminate(true);

			mAdapter = new ArrayAdapter<CitationView>(this, android.R.layout.Favourite_citations, fav_citations_view);
			setListAdapter(mAdapter);
			setReference();
			progressbar.setIndeterminate(false);

	}

	@Override
	public void setReference()
	{
		root = LayoutInflater.from(this).inflate(android.R.id.content, container);

		progressbar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT, Garavity.CENTER));
		getListView(lView).setEmptyView(progressbar);
		root.addView(progressbar);

		ButterKnife.Bind(this, root);

	}
	// ParseObject -> Item -> CitationView -> CitationView array
	public static CitationView[] getCurrUserFavouriteViewsArray()
	{
		ParseUser curr = ParseUser.getCurrentUser();
		if(curr != null)
		{
			ParseQuery<ParseObject> fav = curr.getQuery("favourite");
			List<Item> citations = fav.stream().map((o) -> ParseStorage.FromParseObject(o)).collect(Collectors.toList());
			CitationView[] citations_views_array = citations.stream().map((e) -> {new CitationView(e, this)}).toArray(CitationView[]::new);

			return citations_views_array;
		}
		else
		{
			startActivity(new Intent(this, LoginActivity.class));
		}
	}

	@Override
	public void OnListItemClick(ListView lView, View citation_View, int position, long id)
	{
		View selected = lView.getItemAtPosition(position);
		CitationView c_v = (CitationView) selected;

		startActivity(new Intent(c_v.this, new CitationActivity(c_v)));
	}

}
