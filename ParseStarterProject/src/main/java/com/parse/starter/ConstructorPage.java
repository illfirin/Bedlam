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

import java.util.*;
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
	protected Drawable im;
	final static protected String pathToFonts = "/system/fonts";


	@Override
	protected void OnCreate(Bundle savedInstance)
	{
		View focusView = null;
		boolean canceled = false;
		super.OnCreate(savedInstance);
		LinearLayout choosersPlaseholder = new LinearLayout(this);
		choosersPlaseholder.setLayouttParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARRENT, LayoutParams.WRAP_CONTENT));
		setContentView(R.layout.activity_constructor);
		author = (TextView) findViewByid(R.id.author_constructor);
		content = (TextView) findViewByid(R.id.content_constructor);
		im = null;
		static final int defaultTextSize = 14;
		ArrayList<String> data = getIntent().getPlacebleArrayListExtra("citation");
		fontSize = (EditText) findViewById(R.id.fontSize);
		fontChooser = (Spinner) findViewById(R.id.fontChooser);
		colourChoose = (Spinner) findViewById(R.id.colourChooser);
		imagePlaceholder = (View)findViewById(r.id.constructor_image);
		im = null;

		content.setContent(data.get(0));
		author.setContent(data.get(1));

		content.setTextSize(defaultTextSize);
		author.setTextSize(defaultTextSize);

		List<File> system_fonts = getAllFonts(path);
		List<String> fonts_name = system_fonts.stream().forEach
			(
				c -> ConstructorPage.FromFileToString(c)
			);
 		HashMap<String, File> fonts_map = new HashMap<String, File>();
		for(int i; i < system_fonts.size(); i++)
		{
			fonts_map.put(fonts_name(i), system_fonts(i));
		}

		ArrayAdapter<CharSequence> fAdapter = ArrayAdapter.createFromResource(this, fonts_name, R.layout.fontChooser);
		fAdapter.setDropDownViewResource(R.layout.simple_dropdown);
		fontChooser.setAdapter(fAdapter);


		ArrayAdapter<CharSequence> cAdapter = ArrayAdapter.createFromResource(this, R.array.colours_array, R.layout.colourChooser);
		cAdapter.setDropDownViewResource(R.layout.simple_dropdown);
		colourChoose.setAdapter(cAdapter);
		colourChoose.setOnItemSelectedListener(new OnItemSelectedListener ()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent , View view, int pos, long id)
			{
				canceled = false;
				String col = ((TextView)parent.getItemAtPosition(pos)).getText();
				author.setTextColourFromHex(col);
				content.setTextColourFromHex(col);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView)
			{
				canceled = true;
				focusView = parentView;
			}

		});



		fontChooser.setOnItemClick(new OnItemSelectedListener()
		{
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
			{
				canceled = false;
				String font_string = ((TextView)parent.getItemAtPosition(pos).getText());
				if(@NonNull fonts_map.get(font_string))
				{
					TypeFace tf = TypeFace.createFromFile(fonts_map.get(font_string));
				}
				author.setTypeface(tf);
				content.setTypeface(tf);
			}
			@Override
			public void onNothingSelected(AdapterView<?> par)
			{
				canceled = true;
				focusView = par;
			}
		})

		fontsize.addTextChangedListener(new TextWatcher()
		{
			@Override
			public void onTextChanged(CharSequence s)
			{
				if(@NonNull fontSize.getText())
				{
					content.setTextSize(fontsize.getText());
					author.setTextSize(fontsize.getText());
				}
			}
		});
		//Open data storage to choose image
		chooseImage.setOnClickListener(OnClickListener c ->
		{
			Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
			intent.addCategory(Intent.CATEGORY_OPENABLE);
			intent.setType("image/*")

			startActivityForResult(intent, 42);
		});

	}
	//When image is choosen get data as uri and set placeholder image
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

	@Override
	public static List<File> getAllFonts(string dir);
	{
		File d_file = new File(dir);

		if(@NonNull dir && @NonNull d_file)
		{
			List<File> fl = (List<File>)
				(FileUtils.listFiles(d_file,
					TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE));
			return fl;
		}

		else
		{
			//if nothing is founded return null-List
			return new List<File>(null);
		}
	}

	@Override
	public static String FromFileToString (File l)
	{
		String s = l.getName();
		//find the
		indx = s.indexOf('.')
		return s.substring(0, indx);
	}

}
