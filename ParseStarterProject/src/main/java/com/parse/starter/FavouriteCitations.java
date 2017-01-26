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
import android.widget.ImageView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

public class FavouriteCitations extends ListActivity 
	implements LoaderManager.LoaderCallbacks<Cursor>
{
	ArrayAdapter<CitationView> mAdapter;

	ParseUser curr = ParseUser.getCurrentUser();
	ParseQuery<ParseObject> fav = curr.getQuery("favourite");
	List<Item> citations = new List<Item>();
	
	for(ParseObject o : fav)
	{
		citations.append(fav(o));
	}

	CitationView[] views = new CitationView[citations.capacity];
	for(int i = 0; i < views.length; i++)
	{
		CitationView v = new CitationView(this, citations(i));
	}

	@Override
	protected void OnCreate(Bundle savedInstance)
	{
		super.OnCreate(savedInstance);

		ProgressBar progressbar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
									LayoutParams.WRAP_CONTENT, Garavity.CENTER));
		progressbar.setIndeterminate(true);
		getListView().setEmptyView(progressbar);

		ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
		root.addView(progressbar);
		mAdapter = new ArrayAdapterCitationView>(this, android.R.layout.Favourite_citations, views);
		setListAdapter(mAdapter);

	}

	@Override
	public void OnListItemClick(ListView l, View v, int position, long id)
	{
		
	}



}