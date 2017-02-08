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
	View imagePlaceholder;
	Spinner fontChooser;
	TextView fontSize;
	Spinner colourChoose;
	ImageView chooseImage;	
	@Override
	protected void OnCreate(Bundle savedInstance)
	{
		super.OnCreate(savedInstance);
		LinearLayout choosersPlaseholder = new LinearLayout(this);
		choosersPlaseholder.setLayouttParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARRENT, LayoutParams.WRAP_CONTENT));
		setContentView(R.layout.activity_constructor);

		ArrayList<String> data = getIntent().getPlacebleArrayListExtra("citation");
		fontSize = (TextView) findViewById(R.id.fontSize);
		fontChooser = (Spinner) findViewById(R.id.fontChooser);
		colourChoose = (Spinner) findViewById(R.id.colourChooser);
		
	}

	chooseImage.setOnClickListener(OnClickListener c ->
		{
			
		});
}