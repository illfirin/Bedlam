//add all dependencies needed
public class LoginWithSocialMedia extends BaseActivity
{
    private ViewGroup root;
    @Bind(R.layout.login_vk)
    protected Button mVkLogin;
    @Bind(R.layout.login_google)
    protected Button mGoogleLogin;
    @Bind(R.layout.login_twitter)
    protected Button mTwitterLogin;
    @Bind(R.layout.login_facebook)
    protected Button mFacebook;

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setReference();
    }

    @OnClick(android.R.layout.login_vk)
    public void login_vk()
    {

    }

    @OnClick(android.R.layout.login_google)
    public void login_google()
    {

    }

    @OnClick(android.R.layout.login_twitter)
    public void login_twitter()
    {

    }

    @OnClick(android.R.layout.login_vk)
    public void login_vk()
    {

    }

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

}
