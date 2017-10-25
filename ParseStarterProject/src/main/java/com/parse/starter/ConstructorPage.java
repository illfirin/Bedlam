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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.onItemSelected;
import butterknife.onTextChanged;
import butterknife.onNothingChanged;
import com.google.android.gms.ads.AdView;
//TODO: remove too much dependencies
public class ConstructorPage extends BaseActivity
{
	protected View root;
	protected ProgressBar progressbar;
	public boolean canceled = false;
	View focusView;
	@Bind(R.id.constructor_image)
	ImageView imagePlaceholder;
	@Bind(R.id.fontChooser)
	Spinner fontChooser;
	@Bind(R.id.fontSize)
	EditText fontSize;
	@Bind(R.id.colourChooser)
	protected Spinner colourChoose;
	@Bind(R.id.author_constructor)
	protected TextView author;
	@Bind(R.id.content_constructor)
	protected TextView content;
	@Bind(R.id.chooseImage_constructor)
	protected ImageView chooseImage;
	@Bind(R.id.image_placeholder)
	protected Drawable im  = null;
	protected static final String pathToFonts = "/system/fonts";
	static final int defaultTextSize = 14;
	private AdView mAdView;


	@Overrides
	protected void OnCreate(Bundle savedInstance)
	{
		super.onCreate(savedInstance);
		setReference();
		setSimpleToolbar(true);

		mAdView = new AdView(this);
        mAdView.size(AdSize.BANNER);
        mAdView.setAdUnitId(R.string.constructor_ad_unitId);

		mAdView = (AdView) findViewById(R.id.View);
		AdRequest adReq = new AdRequest.Builder().build();
		mAdView.loadAd(adReq);
	}
	@OnItemSelected(R.id.colourChooser)
	public void onItemSelected(AdapterView<?> parent , View view, int pos, long id)
	{
		canceled = false;
		String col = ((TextView)parent.getItemAtPosition(pos)).getText();
		author.setTextColourFromHex(col);
		content.setTextColourFromHex(col);
	}

	@onNothingSelected(r.id.colourChooser)
	public void onNothingSelected(AdapterView<?> parentView)
	{
		canceled = true;
		focusView = parentView;
	}

	@OnClick(R.id.choose_Image)
	public void onClick()
	{
		progressbar.setIndeterminate(true);
		Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		startActivityForResult(intent, 42);
		progressbar.setIndeterminate(false);
	}

	@OnTextChanged(R.id.fontSize)
	public void onTextChanged(CharSequence c)
	{
		if(@NonNull fontSize.getText())
		{
			content.setTextSize(fontsize.getText());
			author.setTextSize(fontsize.getText());
		}
	}

	@onItemSelected(R.id.fontChooser)
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

	@onNothingSelected(R.id.fontChooser)
	public void onNothingSelected(AdapterView<?> parent)
	{
		canceled = true;
		focusView = parentView;
	}
	@Override
	public void setReference()
	{
		root = LayoutInflater.from(this).inflate(R.id.constrictor_layout);
		progressbar = new ProgressBar(this);
		progressbar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT, Garavity.CENTER));

		getLinearLayout(root).setEmptyLayout(progressbar);
		ButterKnife.Bind(this, root);
		LinearLayout choosersPlaseholder = new LinearLayout(this);
		choosersPlaseholder.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARRENT, LayoutParams.WRAP_CONTENT));
		ArrayList<String> data = getIntent().getPlacebleArrayListExtra("citation");

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
	}


	@Override
	public static List<File> getAllFonts(string dir)
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
