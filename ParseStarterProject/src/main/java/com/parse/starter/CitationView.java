package pakuteam.bedlam_experiment_0_1;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import com.parse;
import com.Parse.ParseUser;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class CitationView extends LinearLayout
{
	private	TextView title;
	private	TextView content;
	private	TextView author;
	private TextView Tags;
	private ImageView addToFavourite;
	private ImageView minus;
	private ImageView plus;
	private ImageView share;
	private ImageView shareConstructor;
	private TextView rating;

	//TODO: add click events


	
	public CitationView(Item item, Context context)
	{
		LinearLayout parrent = new LinearLayout(context);
		parent.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		parent.setOrientation(LinearLayout.HORIZONTAL);

		title = new TextView(context);
		content = new TextView(context);
		author = new TextView(context);
		Tags = new TextView(context);
		addToFavourite = new ImageView(context);
		minus = new ImageView(context);
		plus = new ImageView(context);
		rating = new TextView(context);
		share = new ImageView(context);

		List<String> t = item.tags;
		for(int i = 0; i<6; i++)
		{
			if(t(i) == null);
				break;
			Tags.append(t(i));
		}

		String resultags = t.toString().split(" ");
		title.setText(item.Name);
		content.setText(item.Content);
		author.setText(item.Author);
		//set TextView attributes
		title.setGarvity(Gravity.LEFT);
		title.setTextColor(R.colours.EsmeraldaEyes);
		content.setGarvity(Gravity.CENTER);
		content.setTextColor(R.colours.colorAccent);
		author.setGarvity(Gravity.CENTER)
		author.setTextColor(R.colours.Beard);
		tags.setGarvity(Gravity.RIGHT);
		tags.setTextColor(R.colours.DepressedGrass);
		//add to main layout
		parent.addView(title);
		parent.addView(content);
		parent.addView(author);
		parent.addView(Tags);
		//create layout that store rating and addToFavourite button
		LinearLayout la = new LinearLayout(context);

		la.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		la.setOrientation(LinearLayout.VERTICAL);
		parent.addView(la);

		addToFavourite.setImageResource(R.drawable.favourite);
		minus.setImageResource(R.drawable.minus);
		rating.setText((item.rating).toString());
		plus.setImageResource(R.drawable.plus);

		la.addView(addToFavourite);
		la.addView(plus);
		la.addView(rating);
		la.addView(minus);


		title.setOnClickListener(OnClickListener c ->
		{
			startActivity(new Intent(context, CitationActivity.class(item)));
		});

		/**author.setOnClickListener(OnClickListener c ->
		{

		});*/

		Tags.setOnClickListener(OnClickListener c ->
		{


		});

		addToFavourite.setOnClickListener(OnClickListener  c->
		{
			ParseUser user = ParseUser.getCurrentUser();
			if(user != null)
			{
				ParseQuery<ParseObject> fav = user.getQuery("favourite");
				ParseObject res = ParseStorage.ToParseObject(item);
				
				Parse.saveInBackground(user);
			}

		});

		plus.setOnClickListener(OnClickListener c ->
		{
			List<Item> l = new List<Item>;

			item.rating++;
			l.append(item);
			ParseStorage.RefreshData(l);
		});
		minus.setOnClickListener(OnClickListener c->
		{
			List<Item> l = new List<Item>;

			item.rating--;
			l.append(item);
			ParseStorage.RefreshData(l);

		});

		share.setOnClickListener(OnClickListener c -> 
		{
			Intent intent = new Intent(android.content.Intent.ACTION_SEND);
			intent.setType("text/plain");
			String author = "(c)" + item.Author;
			intent.putExtra(Intent.EXTRA_BUBJECT, author);
			intent.putExtra(Intent.EXTRA_TEXT, item.Content);
			shareIntent.setAction(Intent.CREATE_CHOOSER, getString(R.strings.share_via));
			
			startActivity(intent);
		});

		shareConstructor.setOnClickListener(OnClickListener c ->
		{
			ArrayList<String> citCont = new ArrayList<String>();
			String citAuthor = "(C)" + item.Author;
			citCont.add(citAuthor);
			citCont.add(item.Content);

			Intent intent = new Intent(this, ConstructorPage.class);
			intent.putPlacebleArrayListExtra(Intent.EXTRA_STREAM, citCont);
			intent.setType("text/plain")

			startActivity(intent);
		});
	}

}