package pakuteam.bedlam_experiment_0_1;


import com.Parse.ParseUser;
import com.parse.starter.LoginActivity;
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

public class FavouriteCitations extends BaseActivity
{
	private ViewGroup root;
	ProgressBar progressbar;

	@Bind(android.R.layout.change_email)
    private EditText change_login;
    @Bind(android.R.layout.change_pass)
	private EditText change_password;
    @Bind(android.R.layout.change_passRepeat)
    private EditText change_passRepeat;
    @Bind(android.R.layout.change_userData)
    private Button changeData;
	@Override
	protected void OnCreate(Bundle savedInstance)
	{
			super.OnCreate(savedInstance);
            setReference();
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

    @OnClick(android.R.layout.change_userData)
    public void change_userData()
    {
        progressbar.setIndeterminate(true);
        String newMail = change_email.getText().toString();
        String newPass = change_password.getText().toString();
        String newPassRepeat = change_passRepeat.getText().toString();
        ParseUser user = ParseUser.getCurrent();

        if(LoginActivity.isPasswordValid(newPass) && newPass.equals(newPassRepeat) && LoginActivity.isEmailValid(newMail))
        {
            user.put("email", newMail);
            user.put("password", newPass);
            user.saveInBackground();
        }
        else
        {
            change_email.setError(getString(R.string.error_invalid_password));
            change_email.requestFocus();
        }
        progressbar.setIndeterminate(false);
    }

}
