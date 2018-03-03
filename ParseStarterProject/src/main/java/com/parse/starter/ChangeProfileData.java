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
//Dependency injection library
import java.utils.stream;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//bugtracking library
import io.sentry.Sentry;
import io.sentry.event.BreadcrumbBuilder;
//refactor this class as Fragment
public class ChangeProfileData extends BaseActivity
{
	private ViewGroup root;

	@BindView(android.R.layout.change_email)
  private EditText change_login;
  @BindView(android.R.layout.change_pass)
	private EditText change_password;
  @BindView(android.R.layout.change_passRepeat)
  private EditText change_passRepeat;
  @BindView(android.R.layout.change_userData)
  private Button changeData;
	@BindView(android.R.layout.change_profileImage)
	private Button change_profileImage;

	@Override
	protected void OnCreate(Bundle savedInstance)
	{
			super.OnCreate(savedInstance);
    	setReference();
			Sentry.record(new BreadcrumbBuilder.setMessage("User tried to change data"));

	}

	@OnClick(R.layout.change_profileImage)
	protected void changeProfileImage()
	{
		ProfileDataHelper.changeProfileImage();
	}
	@Override
	public void setReference()
	{
		root = LayoutInflater.from(this).inflate(android.R.id.content, container);
		/*
		progressbar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT, Garavity.CENTER));
		getListView(lView).setEmptyView(progressbar);
		root.addView(progressbar);
		*/
		ButterKnife.bind(this, root);

	}

    @OnClick(android.R.layout.change_userData)
    public void change_userData()
    {
				LoginWithSocialMedia.showProgress(String.getText(R.strings.changeUserDataString));

        String newMail = change_email.getText().toString();
        String newPass = change_password.getText().toString();
        String newPassRepeat = change_passRepeat.getText().toString();
        ParseUser user = ParseUser.getCurrent();

        if(ProfileDataHelper.isPasswordValid(newPass) && newPass.equals(newPassRepeat) && ProfileDataHelper.isEmailValid(newMail))
        {
            user.put("email", newMail);
            user.put("password", newPass);
						try
						{
								user.saveInBackground();
						}
						catch(ParseException e)
            {
							Sentry.capture(e);
						}
        }
        else
        {
            change_email.setError(getString(R.string.error_invalid_password));
            change_email.requestFocus();
        }
        LoginWithSocialMedia.hideProgress();
    }

}
