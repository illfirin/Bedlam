public class LoginWithSocialMedia extends BaseActivity
{
    private ViewGroup root;
    protected Button mVkLogin;
    protected Button mGoogleLogin;
    protected Button mTwitterLogin;
    protected Button mInstagram

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setReference();
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
