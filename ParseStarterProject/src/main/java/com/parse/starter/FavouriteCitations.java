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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavouriteCitations extends BaseActivity
	implements LoaderManager.LoaderCallbacks<Cursor>
{
	private ViewGroup root;
	ProgressBar progressbar;

	@Bind(android.R.layout.Favourite_citations)
	protected ListView lView;



	private ArrayAdapter<CitationView> mAdapter;
	protected List<Item> fav_citations = getFavourite();


	private CitationView[] views = new CitationView[citations.size()];
	for(int i = 0; i < views.length; i++)
	{
		CitationView v = new CitationView(this, citations(i));
	}

	@Override
	protected void OnCreate(Bundle savedInstance)
	{
			super.OnCreate(savedInstance);

			progressbar.setIndeterminate(true);
			mAdapter = new ArrayAdapter<CitationView>(this, lView, views);
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

	public List<Item> getFavourite()
	{
		private ParseUser curr = ParseUser.getCurrentUser();
		private ParseQuery<ParseObject> fav = curr.getQuery("favourite");
		private List<Item> citations = new ArrayList<Item>();

		for(ParseObject o : fav)
		{
			citations.add(fav(o));
		}
		return citations;
	}

	@Override
	public void OnListItemClick(ListView lView, View citation_View, int position, long id)
	{
		View selected = lView.getItemAtPosition(position);
		CitationView c_v = selected;
		startActivity(new Intent(c_v.this, CitationViewActivity.class(c_v)));
	}



}
