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

public class SearchPage extends LinearLayout 
		implements LoaderManager.LoaderCallbacks<Cursor>
{
	AutoCompleteTextView searchText;
	Button find;
	ListView founded;
	ArrayAdapter<CitationView> lAdapter;
	List<CitationView> searchedView;
	View focusView = null;

	@Override
	protected void OnCreate(Bundle savedInstance)
	{
		super.OnCreate(savedInstance);
		setContentView(R.Layout.activity_search);

		lAdapter = new ArrayAdapter()
		searchText = (AutoCompleteTextView) findViewById(R.id.searchText);
		find = (AutoCompleteTextView) findViewById(R.id.find);
		founded = (ListView) findViewById(R.id.founded);

		ProgressBar progressbar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
									LayoutParams.WRAP_CONTENT, Garavity.CENTER));
		
		getLinearLayout().setEmptyLayout(progressbar);

		searched = new ArrayList<CitationView>();
		string s = searchText.getText();
		
		//Так вообще можно делать????
		button.setOnClickListener(OnClickListener () -> 
		{
			attemptSearch(s);

		});
		if(cancel)
		{
			focusView.requestfocus();
		}

		CitationView[] viewArr = new CitationView[searched.size()];
		viewArr = searched.toArray(viewArr);
		lAdapter = new ArrayAdapter<CitationView>(this, founded, viewArr);
	}

	private void attemptSearch(string text)
	{

		boolean cancel = false;
		ParseQuery<ParseObject> searchQuery = ParseObject.getQuery("Citations");
		searchQuery.whereEqualTo("Content", text);
		searchQuery.findInBackground(new FindCallBack<ParseObject>
		{
			public void done(List<ParseObject> citationsList, ParseException e)
			{
				if(e == null)
				{
					progressbar.setProgressBarIndeterminateVisibility(true);
					citationsList.stream()
								.forEach(o ->
						{
							Item i = ParseStorage.FromParseObject(o);
							CitationView contentView = new CitationView(this, i);
							searched.add(contentView);
						});
						
					progressbar.setProgressBarIndeterminateVisibility(false);
				}
				else
				{
					searched.setError(R.string.error_cannot_find);
					focusView = searched;
					cancel = true; 
				}	
			});
	}


}