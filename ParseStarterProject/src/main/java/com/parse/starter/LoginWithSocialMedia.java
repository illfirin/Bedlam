//add all dependencies needed and package

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LoginWithSocialMedia extends extends ActionBarActivity implements FragmentManager.OnBackStackChangedListener
{
    /*
    private ViewGroup root;
    @Bind(R.layout.login_vk)
    protected Button mVkLogin;
    @Bind(R.layout.login_google)
    protected Button mGoogleLogin;
    @Bind(R.layout.login_twitter)
    protected Button mTwitterLogin;
    @Bind(R.layout.login_facebook)
    protected Button mFacebook;
    */
    public static final String SOCIAL_NETWORK_TAG = "SocialIntegrationMain.SOCIAL_NETWORK_TAG";
    private static ProgressDialog pd;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setReference();
        context = this;

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        homeAsUpByBackStack();

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MainFragment())
                .commit();
        }
    }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    return true;
  }

  @Override
  public boolean onBackStackChanged()
  {
    homeAsUpByBackStack();
  }

  private void homeAsUpByBackStack()
  {
    int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
    if(backStackEntryCount > 0)
    {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    else
    {
      getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch(item.getItemId())
    {
      case android.R.id.home:
        getSupportFragmentManager().popBackStack();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  protected static void showProgress(String message, Context context, ProgressDialog pd)
  {
    pd = new ProgressDialog(context);
    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

    pd.setMessage(message);
    pd.setCancelable(false);
    pd.setCanceledOnTouchOutside(false);
    pd.show();
  }

  protected static void hideProgress(ProgressDialog pd)
  {
    pd.dismiss();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data)

    Fragment fragment = getSupportFragmentManager().findFragmentByTag(SOCIAL_NETWORK_TAG);
    if(fragment != null)
    {
      fragment.onActivityResult(requestCode, resultCode, data);
    }
  }
  /*
  @Override
	public void setReference()
	{
		root = LayoutInflater.from(this).inflate(android.R.id.login_socialMedia);

		progressbar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT, Garavity.CENTER));
		getListView(lView).setEmptyView(progressbar);
		root.addView(progressbar);

		ButterKnife.Bind(this, root);
	}
  */
}
