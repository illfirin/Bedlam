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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Spinner;
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

public class ConstructorPage extends AppCompatActivity  
{
	protected ImageView imagePlaceholder;
	protected Spinner fontChooser;
	protected EditText fontSize;
	protected Spinner colourChoose;
	protected TextView author;
	protected TextView content;
	protected ImageView chooseImage;
	protected Drawable im = null;
	final static protected String pathToFonts = "/system/fonts";


	@Override
	protected void OnCreate(Bundle savedInstance)
	{
		super.OnCreate(savedInstance);
		LinearLayout choosersPlaseholder = new LinearLayout(this);
		choosersPlaseholder.setLayouttParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARRENT, LayoutParams.WRAP_CONTENT));
		setContentView(R.layout.activity_constructor);
		author = (TextView) findViewByid(R.id.author_constructor);
		content = (TextView) findViewByid(R.id.content_constructor);
		static final int defaultTextSize = 14;
		ArrayList<String> data = getIntent().getPlacebleArrayListExtra("citation");
		fontSize = (EditText) findViewById(R.id.fontSize);
		fontChooser = (Spinner) findViewById(R.id.fontChooser);
		colourChoose = (Spinner) findViewById(R.id.colourChooser);
		imagePlaceholder = (View)findViewById(r.id.constructor_image);

		File f = new File(path);
		content.setContent(data(0));
		author.setContent(data(1));

		context.setTextSize(defaultTextSize);
		author.setTextSize(defaultTextSize);

		

		
		ArrayAdapter<CharSequence> cAdapter = ArrayAdapter.createFromResource(this, R.array.colours_array, R.layout.colourChooser);
		cAdapter.setDropDownViewResource(R.layout.simple_dropdown);
		colourChooser.setAdapter(cAdapter);
		
		public void onItemSelected(AdapterView<TextView> , View view, int pos, long id)
		{
			String col = ((TextView)parent.getItemAtPosition(pos)).getText();
			author.setTextColourFromHex(col);
			content.setTextColourFromHex(col);
		}

		fontChooser.setOnItemClick()

		fontsize.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s)
			{
				if(fontSize.getText()!= null)
				{
					content.setTextSize(fontsize.getText());
					aythor.setTextSize(fontsize.getText());
				}
			}
		});

		chooseImage.setOnClickListener(OnClickListener c ->
		{
			Intent intent = new Inten(Intent.ACTION_OPEN_DOCUMENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			intent.setType("image/*")

			startActivityForResult(intent, 42);
		});

	}

	@Override
	public void OnActivityResult(int requestCode, int ResultCode, Intent resultData)
	{
		if(requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK)
		{
			Uri uri = null;
			if(resultData != null)
			{
				uri = resultData.getData();
				imagePlaceholder.setImageUri(null);
				imagePlaceholder.setImageUri(uri);
			}
		}
	}
}